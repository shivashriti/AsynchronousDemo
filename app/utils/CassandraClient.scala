package utils

import com.datastax.driver.core.policies.{ConstantReconnectionPolicy, DowngradingConsistencyRetryPolicy}
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.{Cluster, ResultSetFuture, Session}

/**
  * Created by Shiva on 20/04/2017.
  */

/*This is the connection client for cassandra.
* Ensure that we create a single session and use it throughout
* Creating different connections/sessions will exhaust JVM memory and db ports*/

object CassandraClient {

  private val cluster =
    Cluster.builder()
      .addContactPoint("localhost")
      .withPort(9042)
      .withRetryPolicy(DowngradingConsistencyRetryPolicy.INSTANCE)
      .withReconnectionPolicy(new ConstantReconnectionPolicy(100L))
      .withCredentials("cassandra", "cassandra")
      .withClusterName("Test Cluster")
    .build()

  private val session = cluster.connect("library")

  def getSession(): Session = {
    session
  }

  def close() {
    session.close
    cluster.close
  }
}
