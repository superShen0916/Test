#! /bin/bash

br=$(git branch | grep \* | cut -d ' ' -f2)

if [ $br == 'dev' ]
then 
    echo dev
else
    echo $br
    echo 111
fi
