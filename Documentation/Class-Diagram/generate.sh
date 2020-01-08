#!/bin/bash
echo "Removing files...";
rm "png/pupp.png";
rm "./pupp.png";

echo "Generating image...";
java -jar "jar/plantuml.jar" "src/pupp.iuml";

echo "Moving image...";
mv "src/pupp.png" "png/pupp.png";
