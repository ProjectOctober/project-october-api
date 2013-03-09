/**
 * generated by Scrooge 3.0.5-SNAPSHOT
 */
package cassie

import com.twitter.scrooge.{ThriftException, ThriftStruct, ThriftStructCodec}
import org.apache.thrift.protocol._
import java.nio.ByteBuffer
import com.twitter.finagle.SourcedException
import scala.collection.mutable
import scala.collection.{Map, Set}

/** A Post vector
 * @param vec, the vector of tokens
 */
object PostVector extends ThriftStructCodec[PostVector] {
  val Struct = new TStruct("PostVector")
  val VecField = new TField("vec", TType.LIST, 1)
  val TimeField = new TField("time", TType.I32, 2)

  /**
   * Checks that all required fields are non-null.
   */
  def validate(_item: PostVector) {
    if (_item.vec == null) throw new TProtocolException("Required field vec cannot be null")
  }

  def encode(_item: PostVector, _oproto: TProtocol) { _item.write(_oproto) }
  def decode(_iprot: TProtocol) = Immutable.decode(_iprot)

  def apply(_iprot: TProtocol): PostVector = decode(_iprot)

  def apply(
    vec: Seq[Token] = Seq[Token](),
    time: Int
  ): PostVector = new Immutable(
    vec,
    time
  )

  def unapply(_item: PostVector): Option[Product2[Seq[Token], Int]] = Some(_item)

  object Immutable extends ThriftStructCodec[PostVector] {
    def encode(_item: PostVector, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = {
      var vec: Seq[Token] = Seq[Token]()
      var _got_vec = false
      var time: Int = 0
      var _got_time = false
      var _done = false
      _iprot.readStructBegin()
      while (!_done) {
        val _field = _iprot.readFieldBegin()
        if (_field.`type` == TType.STOP) {
          _done = true
        } else {
          _field.id match {
            case 1 => { /* vec */
              _field.`type` match {
                case TType.LIST => {
                  vec = {
                    val _list = _iprot.readListBegin()
                    val _rv = new mutable.ArrayBuffer[Token](_list.size)
                    var _i = 0
                    while (_i < _list.size) {
                      _rv += {
                        Token.decode(_iprot)
                      }
                      _i += 1
                    }
                    _iprot.readListEnd()
                    _rv
                  }
                  _got_vec = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 2 => { /* time */
              _field.`type` match {
                case TType.I32 => {
                  time = {
                    _iprot.readI32()
                  }
                  _got_time = true
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
      if (!_got_vec) throw new TProtocolException("Required field 'PostVector' was not found in serialized data for struct PostVector")
      if (!_got_time) throw new TProtocolException("Required field 'PostVector' was not found in serialized data for struct PostVector")
      new Immutable(
        vec,
        time
      )
    }
  }

  /**
   * The default read-only implementation of PostVector.  You typically should not need to
   * directly reference this class; instead, use the PostVector.apply method to construct
   * new instances.
   */
  class Immutable(
    val vec: Seq[Token] = Seq[Token](),
    val time: Int
  ) extends PostVector

  /**
   * This Proxy trait allows you to extend the PostVector trait with additional state or
   * behavior and implement the read-only methods from PostVector using an underlying
   * instance.
   */
  trait Proxy extends PostVector {
    protected def _underlying_PostVector: PostVector
    def vec: Seq[Token] = _underlying_PostVector.vec
    def time: Int = _underlying_PostVector.time
  }
}

trait PostVector extends ThriftStruct
  with Product2[Seq[Token], Int]
  with java.io.Serializable
{
  import PostVector._

  def vec: Seq[Token]
  def time: Int

  def _1 = vec
  def _2 = time

  override def write(_oprot: TProtocol) {
    PostVector.validate(this)
    _oprot.writeStructBegin(Struct)
    if (true) {
      val vec_item = vec
      _oprot.writeFieldBegin(VecField)
      _oprot.writeListBegin(new TList(TType.STRUCT, vec_item.size))
      vec_item.foreach { vec_item_element =>
        vec_item_element.write(_oprot)
      }
      _oprot.writeListEnd()
      _oprot.writeFieldEnd()
    }
    if (true) {
      val time_item = time
      _oprot.writeFieldBegin(TimeField)
      _oprot.writeI32(time_item)
      _oprot.writeFieldEnd()
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    vec: Seq[Token] = this.vec, 
    time: Int = this.time
  ): PostVector = new Immutable(
    vec, 
    time
  )

  override def canEqual(other: Any): Boolean = other.isInstanceOf[PostVector]

  override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)

  override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)

  override def toString: String = runtime.ScalaRunTime._toString(this)


  override def productArity: Int = 2

  override def productElement(n: Int): Any = n match {
    case 0 => vec
    case 1 => time
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix: String = "PostVector"
}