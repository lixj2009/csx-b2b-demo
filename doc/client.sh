#!/bin/bash
projectname=$1
path=$(cd $(dirname ${BASH_SOURCE[0]}); pwd)
path=${path/\/doc/}
echo "目录:$path,新项目:$projectname"
cd $path
### check
if [[ $projectname == "" ]]; then
	echo "错误: 请输入新项目名"
	exit 0
fi
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
echo "完毕:循环所有文件 替换com.yh.csx.demo"

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
