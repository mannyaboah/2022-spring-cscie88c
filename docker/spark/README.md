# Spark Cluster in Docker
This section contains instructions on using a spark standalone cluster with 1 master and 1-2 worker nodes.

**Spark version**: 3.2.1

**Scala version**: 2.13.x

## Work with the Spark shell interactively
1. start spark cluster
```
docker-compose up -d

```

2. connect to master node
```
docker exec -it spark_spark-master_1  /bin/bash
```

3. start interactive shell
```
bin/spark-shell
```

4. View container logs
```
docker log <container name>
```


