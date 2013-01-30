package october

import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import org.apache.thrift.protocol._
import java.nio.ByteBuffer
import scala.collection.mutable
import scala.collection.{Map, Set}

object EngineException extends ThriftStructCodec[EngineException] {
  val Struct = new TStruct("EngineException")
  val WhyField = new TField("why", TType.STRING, 1)

  def encode(_item: EngineException, _oproto: TProtocol) { _item.write(_oproto) }
  def decode(_iprot: TProtocol) = Immutable.decode(_iprot)

  def apply(_iprot: TProtocol): EngineException = decode(_iprot)

  def apply(
    `why`: String
  ): EngineException = new Immutable(
    `why`
  )

  def unapply(_item: EngineException): Option[String] = Some(_item.why)

  object Immutable extends ThriftStructCodec[EngineException] {
    def encode(_item: EngineException, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = {
      var `why`: String = null
      var _got_why = false
      var _done = false
      _iprot.readStructBegin()
      while (!_done) {
        val _field = _iprot.readFieldBegin()
        if (_field.`type` == TType.STOP) {
          _done = true
        } else {
          _field.id match {
            case 1 => { /* why */
              _field.`type` match {
                case TType.STRING => {
                  `why` = {
                    _iprot.readString()
                  }
                  _got_why = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case _ => TProtocolUtil.skip(_iprot, _field.`type`)
          }
          _iprot.readFieldEnd()
        }
      }
      _iprot.readStructEnd()
      if (!_got_why) throw new TProtocolException("Required field 'why' was not found in serialized data for struct EngineException")
      new Immutable(
        `why`
      )
    }
  }

  /**
   * The default read-only implementation of EngineException.  You typically should not need to
   * directly reference this class; instead, use the EngineException.apply method to construct
   * new instances.
   */
  class Immutable(
    val `why`: String
  ) extends EngineException

}

trait EngineException extends Exception with ThriftStruct
  with Product1[String]
  with java.io.Serializable
{
  import EngineException._

  def `why`: String

  def _1 = `why`

  override def write(_oprot: TProtocol) {
    validate()
    _oprot.writeStructBegin(Struct)
    if (true) {
      val `why_item` = `why`
      _oprot.writeFieldBegin(WhyField)
      _oprot.writeString(`why_item`)
      _oprot.writeFieldEnd()
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    `why`: String = this.`why`
  ): EngineException = new Immutable(
    `why`
  )

  /**
   * Checks that all required fields are non-null.
   */
  def validate() {
    if (`why` == null) throw new TProtocolException("Required field 'why' cannot be null")
  }

  def canEqual(other: Any) = other.isInstanceOf[EngineException]

  override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)

  override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)

  override def toString: String = runtime.ScalaRunTime._toString(this)

  override def productArity = 1

  override def productElement(n: Int): Any = n match {
    case 0 => `why`
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix = "EngineException"
}