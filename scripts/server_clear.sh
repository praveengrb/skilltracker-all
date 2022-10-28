#!/bin/bash
sudo su
rm -f -R /home/ec2-user/skilltracker/
mkdir -p /home/ec2-user/skilltracker/

sudo yum -y install java 
sudo yum -y update
sudo yum -y install ruby
sudo yum -y install wget

sudo service codedeploy-agent stop
cd /home/ec2-user
wget https://aws-codedeploy-us-east-1.s3.us-east-1.amazonaws.com/latest/install
chmod +x ./install
sudo ./install auto

sudo service codedeploy-agent start