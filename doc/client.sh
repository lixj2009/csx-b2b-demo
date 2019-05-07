#!/bin/bash
### 使用命令格式:
### curl -O http://10.252.192.3/csx-public/csx-b2b-demo/raw/{version}/doc/client.sh
### sh client.sh {projectname} {version}
### 示范:
### curl -O http://10.252.192.3/csx-public/csx-b2b-demo/raw/master/doc/client.sh
### sh client.sh test
### 
### by 车江毅
### 

projectname=$1
version=$2 #默认master

if [[ $projectname == "" ]]; then
	echo "错误: 请输入新项目名"
	exit 0
fi
if [[ $version == "" ]]; then
	version="master"
fi

### 目录
path=$(cd $(dirname $0); pwd)
path=$path/csx-b2b-$projectname
echo "当前目录:$path,新项目名:$projectname,脚手架版本:$version"

### 下载项目模板
rm -rf csx-b2b-demo-${version}.tar.gz
curl -O http://10.252.192.3/csx-public/csx-b2b-demo/-/archive/${version}/csx-b2b-demo-${version}.tar.gz
tar -zxvf csx-b2b-demo-${version}.tar.gz
mv csx-b2b-demo-${version} csx-b2b-$projectname
echo "完毕下载模板:csx-b2b-demo-${version}.tar.gz"

###循环所有文件 替换com.yh.csx.demo
function loopdic(){
  for f in `ls $1`
  do
    if [ -d $1"/"$f ];then
      loopdic $1"/"$f
    else
      local path2=$1"/"$f 
      local name2=$f
	  #if [[ $path2 == *client.sh ]]; then echo '打印$path2' ;fi
	  if [[ ($path2 == *.java) || ($path2 == *.xml) || ($path2 == *.properties) || ($path2 == *.md) ]]; then   
		 echo "扫描文件: ${path2}"
		  sed -i "s/com.yh.csx.demo/com.yh.csx.$projectname/g" $path2
		  sed -i "s/csx-b2b-demo/csx-b2b-$projectname/g" $path2 
		  sed -i "s/swagger.title=DEMO/swagger.title=$projectname/g" $path2
	  fi
	  
    fi
  done
}
loopdic $path
echo "完毕:循环所有文件并替换demo信息"

### 循环替换模块文件夹
dirs=('csx-b2b-demo-core' 'csx-b2b-demo-api-provider' 'csx-b2b-demo-dao' 'csx-b2b-demo-service' 'csx-b2b-demo-api' 'csx-b2b-demo-task')
for d in ${dirs[@]}
do
    newd=${d/demo/${projectname}}
	echo "扫描文件夹: $path/$d/"
    mv $path/$d/src/main/java/com/yh/csx/demo $path/$d/src/main/java/com/yh/csx/$projectname
	mv $path/$d $path/$newd
done
echo "完毕:循环模块文件夹重命名所有demo目录"
### csx-b2b-demo-core

### csx-b2b-demo-api-provider


### csx-b2b-demo-dao


### csx-b2b-demo-service


### csx-b2b-demo-task


### csx-b2b-demo-api


### csx-b2b-demo


echo "全部完成！！！享受它把~~~~"

echo "by 车江毅"
