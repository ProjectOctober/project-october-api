package october

import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import org.apache.thrift.protocol._
import java.nio.ByteBuffer
import scala.collection.mutable
import scala.collection.{Map, Set}

object PostList extends ThriftStructCodec[PostList] {
  val Struct = new TStruct("PostList")
  val ConfidenceField = new TField("confidence", TType.DOUBLE, 1)
  val PostsField = new TField("posts", TType.LIST, 2)

  def encode(_item: PostList, _oproto: TProtocol) { _item.write(_oproto) }
  def decode(_iprot: TProtocol) = Immutable.decode(_iprot)

  def apply(_iprot: TProtocol): PostList = decode(_iprot)

  def apply(
    `confidence`: Option[Double] = None,
    `posts`: Seq[Post] = Seq[Post]()
  ): PostList = new Immutable(
    `confidence`,
    `posts`
  )

  def unapply(_item: PostList): Option[Product2[Option[Double], Seq[Post]]] = Some(_item)

  object Immutable extends ThriftStructCodec[PostList] {
    def encode(_item: PostList, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = {
      var `confidence`: Double = 0.0
      var _got_confidence = false
      var `posts`: Seq[Post] = Seq[Post]()
      var _got_posts = false
      var _done = false
      _iprot.readStructBegin()
      while (!_done) {
        val _field = _iprot.readFieldBegin()
        if (_field.`type` == TType.STOP) {
          _done = true
        } else {
          _field.id match {
            case 1 => { /* confidence */
              _field.`type` match {
                case TType.DOUBLE => {
                  `confidence` = {
                    _iprot.readDouble()
                  }
                  _got_confidence = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 2 => { /* posts */
              _field.`type` match {
                case TType.LIST => {
                  `posts` = {
                    val _list = _iprot.readListBegin()
                    val _rv = new mutable.ArrayBuffer[Post](_list.size)
                    var _i = 0
                    while (_i < _list.size) {
                      _rv += {
                        Post.decode(_iprot)
                      }
                      _i += 1
                    }
                    _iprot.readListEnd()
                    _rv
                  }
                  _got_posts = true
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
      if (!_got_posts) throw new TProtocolException("Required field 'posts' was not found in serialized data for struct PostList")
      new Immutable(
        if (_got_confidence) Some(`confidence`) else None,
        `posts`
      )
    }
  }

  /**
   * The default read-only implementation of PostList.  You typically should not need to
   * directly reference this class; instead, use the PostList.apply method to construct
   * new instances.
   */
  class Immutable(
    val `confidence`: Option[Double] = None,
    val `posts`: Seq[Post] = Seq[Post]()
  ) extends PostList

  /**
   * This Proxy trait allows you to extend the PostList trait with additional state or
   * behavior and implement the read-only methods from PostList using an underlying
   * instance.
   */
  trait Proxy extends PostList {
    protected def _underlyingPostList: PostList
    def `confidence`: Option[Double] = _underlyingPostList.`confidence`
    def `posts`: Seq[Post] = _underlyingPostList.`posts`
  }
}

trait PostList extends ThriftStruct
  with Product2[Option[Double], Seq[Post]]
  with java.io.Serializable
{
  import PostList._

  def `confidence`: Option[Double]
  def `posts`: Seq[Post]

  def _1 = `confidence`
  def _2 = `posts`

  override def write(_oprot: TProtocol) {
    validate()
    _oprot.writeStructBegin(Struct)
    if (`confidence`.isDefined) {
      val `confidence_item` = `confidence`.get
      _oprot.writeFieldBegin(ConfidenceField)
      _oprot.writeDouble(`confidence_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `posts_item` = `posts`
      _oprot.writeFieldBegin(PostsField)
      _oprot.writeListBegin(new TList(TType.STRUCT, `posts_item`.size))
      `posts_item`.foreach { `_posts_item_element` =>
        `_posts_item_element`.write(_oprot)
      }
      _oprot.writeListEnd()
      _oprot.writeFieldEnd()
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    `confidence`: Option[Double] = this.`confidence`,
    `posts`: Seq[Post] = this.`posts`
  ): PostList = new Immutable(
    `confidence`,
    `posts`
  )

  /**
   * Checks that all required fields are non-null.
   */
  def validate() {
    if (`posts` == null) throw new TProtocolException("Required field 'posts' cannot be null")
  }

  def canEqual(other: Any) = other.isInstanceOf[PostList]

  override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)

  override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)

  override def toString: String = runtime.ScalaRunTime._toString(this)

  override def productArity = 2

  override def productElement(n: Int): Any = n match {
    case 0 => `confidence`
    case 1 => `posts`
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix = "PostList"
}