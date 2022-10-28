#!/bin/bash
sudo su
java -jar /home/ec2-user/skilltracker/skilltracker-discovery-service-3.0-SNAPSHOT.jar >/dev/null 2>&1 &
java -jar /home/ec2-user/skilltracker/skilltracker-config-server-3.0-SNAPSHOT.jar >/dev/null 2>&1 &
java -jar /home/ec2-user/skilltracker/skilltracker-user-service-3.0-SNAPSHOT.jar >/dev/null 2>&1 &
java -jar /home/ec2-user/skilltracker/skilltracker-admin-service-3.0-SNAPSHOT.jar >/dev/null 2>&1 &
java -jar /home/ec2-user/skilltracker/skilltracker-receiver-service-3.0-SNAPSHOT.jar >/dev/null 2>&1 &