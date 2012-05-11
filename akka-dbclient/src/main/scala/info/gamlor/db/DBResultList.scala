package info.gamlor.db

import scala.collection.JavaConversions._
import org.adbcj.{Result, Value, ResultSet}


/**
 * @author roman.stoffel@gamlor.info
 * @since 30.03.12
 */

class DBResultList(val resultSet: ResultSet) extends Seq[DBResultRow] {
  def length = resultSet.size()

  def apply(idx: Int) = new DBResultRow(resultSet.get(idx))
  def apply(rowIndex: Int,columnIndex:Int) = resultSet.get(rowIndex).get(columnIndex)
  def apply(rowIndex: Int,column:String):Value = apply(rowIndex).get(column)

  def get(index:Int) = new DBResultRow(resultSet.get(index))

  def fields = resultSet.getFields.toSeq

  def iterator = {
    val iter = resultSet.iterator()
    new Iterator[DBResultRow] {
      def hasNext = iter.hasNext

      def next() = new DBResultRow(iter.next())
    }
  }
}


class DBResult(val resultSet: Result){
    def affectedRows = resultSet.getAffectedRows;
    val warnings = resultSet.getWarnings.toSeq;
    val generatedKeys = new DBResultList(resultSet.getGeneratedKeys);
}