#!/bin/bash
search_term="breathing"
file_path="/usr/local/Hello.txt"
destination_file="/usr/local/Hello1.txt"

line_number=$(grep -n "$search_term" $file_path | cut -d ":" -f1)
echo "行号：$line_number"
echo "内容：$search_term"
echo "行号：$line_number 内容：$search_term" > $destination_file
