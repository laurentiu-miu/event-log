# event-log


### Start Emulator Locally

```shell
gcloud config set project myTestProject
gcloud beta emulators pubsub start --project=myTestProject
```
[<font color="red">Emultator LIMITATIONS</font>](https://cloud.google.com/pubsub/docs/emulator#known_limitations)

### Create Topic
```shell
#Request
curl -s -X PUT 'http://localhost:8085/v1/projects/my-test-project/topics/myTestTopic'
```
### Create subscription
```shell
curl -s -X PUT 'http://localhost:8085/v1/projects/my-test-project/subscriptions/myTestSubscriptionAll' \
    -H 'content-type: application/json' \
    --data '{"topic":"projects/my-test-project/topics/myTestTopic"}'
    
curl -s -X PUT 'http://localhost:8085/v1/projects/my-test-project/subscriptions/myTestSubscription1' \
    -H 'content-type: application/json' \
    --data '{"topic":"projects/my-test-project/topics/myTestTopic","filter":"attributes.customType=\"START\""}'
    
curl -s -X PUT 'http://localhost:8085/v1/projects/my-test-project/subscriptions/myTestSubscription2' \
    -H 'content-type: application/json' \
    --data '{"topic":"projects/my-test-project/topics/myTestTopic","filter":"attributes.customType=\"FIRST\""}'
    
curl -s -X PUT 'http://localhost:8085/v1/projects/my-test-project/subscriptions/myTestSubscription3' \
    -H 'content-type: application/json' \
    --data '{"topic":"projects/my-test-project/topics/myTestTopic","filter":"attributes.customType=\"SECOND\""}'
```
### Delete subscription
```shell
curl -s -X DELETE 'http://localhost:8085/v1/projects/my-test-project/subscriptions/myTestSubscription1'
```


### SQL Scripts

```sql
--events
select count(*) from public.event_log
--duplicates and errors
select * from public.error_log
--drop tables
drop table public.databasechangelog;
drop table public.databasechangeloglock;
drop table public.event_log;
drop table public.error_log;
drop table public.tab_1;
drop table public.tab_2;
drop table public.tab_3;
--clear data
truncate public.event_log;
truncate public.error_log;
truncate public.tab_1;
truncate public.tab_2;
truncate public.tab_3;
```
