#!/bin/bash
while true
do
        cpu_usage=$(top -bn 1 | grep "%Cpu(s)" | awk '{print $2}')
        cpu_usage=${cpu_usage%.*}
        mem_info=$(free | awk 'NR==3{print $3/$2 * 100}')
        usage=$(df -h | awk '$NF=="/"{printf "%s\n", $5}' | cut -d'%' -f1)
        echo "当前CPU使用：${cpu_usage}%"
        echo "当前内存使用：$mem_info%"
        echo "磁盘使用：$usage%"
        sleep 5
done
