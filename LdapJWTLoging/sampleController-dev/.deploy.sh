systemctl stop httpd.service
lsof -i:9020 | grep java | grep \* | awk '{print $2}' | xargs kill -9
mvn package -Dmaven.test.skip=true
mvn spring-boot:start
nohup mvn spring-boot:run &
systemctl start httpd.service
