for i in $(cat list.txt) ; do
svn --username XXXXXX --password XXXXXX co https://waldo2.dawsoncollege.qc.ca/svn/Samples518/$i
done 
