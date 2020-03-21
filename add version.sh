#!/bin/bash

function pause(){
	read -p "$*"
}

echo "Input path to jar file."
read JAR
echo "Input Group ID."
read GROUP_ID
echo "Input Artifact ID."
read ARTIFACT_ID
echo "Input Version."
read VERSION

pause "Creating a new version as $GROUP_ID.$ARTIFACT_ID v$VERSION. Press enter to continue."

mkdir $VERSION
mv $JAR $VERSION
cd $VERSION
mvn install:install-file -DgroupId=$GROUP_ID -DartifactId=$ARTIFACT_ID -Dversion=$VERSION -Dfile=$JAR -Dpackaging=jar -DgeneratePom=true -DlocalRepositoryPath=.  -DcreateChecksum=true
rm $JAR
pause "Extraction complete. Version $VERSION created. Press enter to close."
