rm -rf student
git clone $1 student 2> ta-output.txt

rm -rf grading
mkdir grading

if [[ -f student/ListExamples.java ]]
then 
    cp student/ListExamples.java grading/
    cp TestListExamples.java grading/
else 
    echo "Missing student/ListExamples.java"
    exit 1
fi

cd grading

CPATH='.;../lib/hamcrest-core-1.3.jar;../lib/junit-4.13.2.jar'
javac -cp $CPATH *.java

if [[ $? -ne 0 ]]
then 
    echo "The program failed to compile"
    exit 1
fi

java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > JUnit-output.txt

lastline=$(cat JUnit-output.txt | tail -n 2 | head -n 1)
OK="OK"
if [[ "$lastline" == *"$OK"* ]]
then
    successes=$(echo $lastline | awk '{print $2}')
    successes="${successes#(}"
    failures="0"
    tests=$successes
else
    tests=$(echo $lastline | awk -F'[, ]' '{print $3}')
    failures=$(echo $lastline | awk  -F '[, ]' '{print $6}')
    successes=$((tests - failures))
fi
echo "Your score is $successes / $tests"
