for i in $(cat list.txt) ; do
svn --username pcampbell --password yellow7snail co https://waldo2.dawsoncollege.qc.ca/svn/Samples518/$i
done 
