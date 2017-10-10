#!/bin/bash
workspace=/data/webapps
svn_tmp=$workspace/tmp
echo "Clean the svn tmp directory..."
if [ ! -d $svn_tmp ]; then
  mkdir $svn_tmp
fi
rm -rf $svn_tmp/*

echo "Bakup the tale directory..."
talebk=$workspace/talebk
if [ ! -d $talebk ]; then
  mkdir $talebk
fi
date=`date +%Y%m%d%H%M%S`
tar -cvf $workspace/tale-$date.tar $workspace/tale
mv $workspace/tale-$date.tar $talebk

echo "Checkout tale project from github..."
svn checkout https://github.com/kekewang/tale/trunk/dist/tale/  $svn_tmp
cp -r $svn_tmp/* $workspace/tale/

echo "Restarting the nine clock website..."
cd $workspace/tale/bin
sh tale.sh stop
sh tale.sh start
