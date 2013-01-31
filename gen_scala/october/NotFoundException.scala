/**
 * generated by Scrooge 3.0.5-SNAPSHOT
 */
package october

import com.twitter.scrooge.{ThriftException, ThriftStruct, ThriftStructCodec}
import org.apache.thrift.protocol._
import java.nio.ByteBuffer
import scala.collection.mutable
import scala.collection.{Map, Set}

/** The queried object does not exist. */
object NotFoundException extends ThriftStructCodec[NotFoundException] {
  val Struct = new TStruct("NotFoundException")

  /**
   * Checks that all required fields are non-null.
   */
  def validate(_item: NotFoundException) {
  }

  def encode(_item: NotFoundException, _oproto: TProtocol) { _item.write(_oproto) }
  def decode(_iprot: TProtocol) = Immutable.decode(_iprot)

  def apply(_iprot: TProtocol): NotFoundException = decode(_iprot)

  def apply(
  ): NotFoundException = new Immutable(
  )

  def unapply(_item: NotFoundException): Boolean = true

  object Immutable extends ThriftStructCodec[NotFoundException] {
    def encode(_item: NotFoundException, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = {
      var _done = false
      _iprot.readStructBegin()
      while (!_done) {
        val _field = _iprot.readFieldBegin()
        if (_field.`type` == TType.STOP) {
          _done = true
        } else {
          _field.id match {
            case _ => TProtocolUtil.skip(_iprot, _field.`type`)
          }
          _iprot.readFieldEnd()
        }
      }
      _iprot.readStructEnd()
      new Immutable(
      )
    }
  }

  /**
   * The default read-only implementation of NotFoundException.  You typically should not need to
   * directly reference this class; instead, use the NotFoundException.apply method to construct
   * new instances.
   */
  class Immutable(
  ) extends NotFoundException

}

trait NotFoundException extends ThriftException with ThriftStruct
  with Product
  with java.io.Serializable
{
  import NotFoundException._



  override def write(_oprot: TProtocol) {
    NotFoundException.validate(this)
    _oprot.writeStructBegin(Struct)
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
  ): NotFoundException = new Immutable(
  )

  override def canEqual(other: Any): Boolean = other.isInstanceOf[NotFoundException]

  override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)

  override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)

  override def toString: String = runtime.ScalaRunTime._toString(this)


  override def productArity: Int = 0

  override def productElement(n: Int): Any = n match {
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix: String = "NotFoundException"
}