package thrift

import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import org.apache.thrift.protocol._
import java.nio.ByteBuffer
import scala.collection.mutable
import scala.collection.{Map, Set}

object Post extends ThriftStructCodec[Post] {
  val Struct = new TStruct("Post")
  val PostIdField = new TField("postId", TType.I64, 1)
  val WeightField = new TField("weight", TType.DOUBLE, 2)

  def encode(_item: Post, _oproto: TProtocol) { _item.write(_oproto) }
  def decode(_iprot: TProtocol) = Immutable.decode(_iprot)

  def apply(_iprot: TProtocol): Post = decode(_iprot)

  def apply(
    `postId`: Long,
    `weight`: Option[Double] = None
  ): Post = new Immutable(
    `postId`,
    `weight`
  )

  def unapply(_item: Post): Option[Product2[Long, Option[Double]]] = Some(_item)

  object Immutable extends ThriftStructCodec[Post] {
    def encode(_item: Post, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = {
      var `postId`: Long = 0L
      var _got_postId = false
      var `weight`: Double = 0.0
      var _got_weight = false
      var _done = false
      _iprot.readStructBegin()
      while (!_done) {
        val _field = _iprot.readFieldBegin()
        if (_field.`type` == TType.STOP) {
          _done = true
        } else {
          _field.id match {
            case 1 => { /* postId */
              _field.`type` match {
                case TType.I64 => {
                  `postId` = {
                    _iprot.readI64()
                  }
                  _got_postId = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 2 => { /* weight */
              _field.`type` match {
                case TType.DOUBLE => {
                  `weight` = {
                    _iprot.readDouble()
                  }
                  _got_weight = true
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
      if (!_got_postId) throw new TProtocolException("Required field 'postId' was not found in serialized data for struct Post")
      new Immutable(
        `postId`,
        if (_got_weight) Some(`weight`) else None
      )
    }
  }

  /**
   * The default read-only implementation of Post.  You typically should not need to
   * directly reference this class; instead, use the Post.apply method to construct
   * new instances.
   */
  class Immutable(
    val `postId`: Long,
    val `weight`: Option[Double] = None
  ) extends Post

  /**
   * This Proxy trait allows you to extend the Post trait with additional state or
   * behavior and implement the read-only methods from Post using an underlying
   * instance.
   */
  trait Proxy extends Post {
    protected def _underlyingPost: Post
    def `postId`: Long = _underlyingPost.`postId`
    def `weight`: Option[Double] = _underlyingPost.`weight`
  }
}

trait Post extends ThriftStruct
  with Product2[Long, Option[Double]]
  with java.io.Serializable
{
  import Post._

  def `postId`: Long
  def `weight`: Option[Double]

  def _1 = `postId`
  def _2 = `weight`

  override def write(_oprot: TProtocol) {
    validate()
    _oprot.writeStructBegin(Struct)
    if (true) {
      val `postId_item` = `postId`
      _oprot.writeFieldBegin(PostIdField)
      _oprot.writeI64(`postId_item`)
      _oprot.writeFieldEnd()
    }
    if (`weight`.isDefined) {
      val `weight_item` = `weight`.get
      _oprot.writeFieldBegin(WeightField)
      _oprot.writeDouble(`weight_item`)
      _oprot.writeFieldEnd()
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    `postId`: Long = this.`postId`,
    `weight`: Option[Double] = this.`weight`
  ): Post = new Immutable(
    `postId`,
    `weight`
  )

  /**
   * Checks that all required fields are non-null.
   */
  def validate() {
  }

  def canEqual(other: Any) = other.isInstanceOf[Post]

  override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)

  override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)

  override def toString: String = runtime.ScalaRunTime._toString(this)

  override def productArity = 2

  override def productElement(n: Int): Any = n match {
    case 0 => `postId`
    case 1 => `weight`
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix = "Post"
}