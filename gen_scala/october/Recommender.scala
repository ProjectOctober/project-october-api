package october

import com.twitter.conversions.time._
import com.twitter.finagle.SourcedException
import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import com.twitter.util.Future
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import org.apache.thrift.protocol._
import org.apache.thrift.TApplicationException
import scala.collection.mutable
import scala.collection.{Map, Set}
import com.twitter.finagle.{Service => FinagleService}
import com.twitter.finagle.stats.{NullStatsReceiver, StatsReceiver}
import com.twitter.finagle.thrift.ThriftClientRequest
import com.twitter.finagle.{Service => FinagleService}
import java.util.Arrays
import org.apache.thrift.transport.{TMemoryBuffer, TMemoryInputTransport, TTransport}
import com.twitter.finagle.builder.{Server, ServerBuilder}
import com.twitter.finagle.stats.{StatsReceiver, OstrichStatsReceiver}
import com.twitter.finagle.thrift.ThriftServerFramedCodec
import com.twitter.finagle.tracing.{NullTracer, Tracer}
import com.twitter.logging.Logger
import com.twitter.ostrich.admin.Service
import com.twitter.util.Duration
import java.util.concurrent.atomic.AtomicReference


object Recommender {
  trait Iface {
    @throws(classOf[TimeoutException])
    def ping(): String
    @throws(classOf[NotFoundException])
    @throws(classOf[EngineException])
    @throws(classOf[TimeoutException])
    def recPosts(`userId`: Long): PostList
  }

  trait FutureIface {
    def ping(): Future[String]
    def recPosts(`userId`: Long): Future[PostList]
  }

  object ping_args extends ThriftStructCodec[ping_args] {
    val Struct = new TStruct("ping_args")
  
    def encode(_item: ping_args, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = Immutable.decode(_iprot)
  
    def apply(_iprot: TProtocol): ping_args = decode(_iprot)
  
    def apply(
    ): ping_args = new Immutable(
    )
  
    def unapply(_item: ping_args): Boolean = true
  
    object Immutable extends ThriftStructCodec[ping_args] {
      def encode(_item: ping_args, _oproto: TProtocol) { _item.write(_oproto) }
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
     * The default read-only implementation of ping_args.  You typically should not need to
     * directly reference this class; instead, use the ping_args.apply method to construct
     * new instances.
     */
    class Immutable(
    ) extends ping_args
  
  }
  
  trait ping_args extends ThriftStruct
    with Product
    with java.io.Serializable
  {
    import ping_args._
  
  
  
    override def write(_oprot: TProtocol) {
      validate()
      _oprot.writeStructBegin(Struct)
      _oprot.writeFieldStop()
      _oprot.writeStructEnd()
    }
  
    def copy(
    ): ping_args = new Immutable(
    )
  
    /**
     * Checks that all required fields are non-null.
     */
    def validate() {
    }
  
    def canEqual(other: Any) = other.isInstanceOf[ping_args]
  
    override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)
  
    override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)
  
    override def toString: String = runtime.ScalaRunTime._toString(this)
  
    override def productArity = 0
  
    override def productElement(n: Int): Any = n match {
      case _ => throw new IndexOutOfBoundsException(n.toString)
    }
  
    override def productPrefix = "ping_args"
  }
  object ping_result extends ThriftStructCodec[ping_result] {
    val Struct = new TStruct("ping_result")
    val SuccessField = new TField("success", TType.STRING, 0)
    val TeField = new TField("te", TType.STRUCT, 1)
  
    def encode(_item: ping_result, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = Immutable.decode(_iprot)
  
    def apply(_iprot: TProtocol): ping_result = decode(_iprot)
  
    def apply(
      `success`: Option[String] = None,
      `te`: Option[TimeoutException] = None
    ): ping_result = new Immutable(
      `success`,
      `te`
    )
  
    def unapply(_item: ping_result): Option[Product2[Option[String], Option[TimeoutException]]] = Some(_item)
  
    object Immutable extends ThriftStructCodec[ping_result] {
      def encode(_item: ping_result, _oproto: TProtocol) { _item.write(_oproto) }
      def decode(_iprot: TProtocol) = {
        var `success`: String = null
        var _got_success = false
        var `te`: TimeoutException = null
        var _got_te = false
        var _done = false
        _iprot.readStructBegin()
        while (!_done) {
          val _field = _iprot.readFieldBegin()
          if (_field.`type` == TType.STOP) {
            _done = true
          } else {
            _field.id match {
              case 0 => { /* success */
                _field.`type` match {
                  case TType.STRING => {
                    `success` = {
                      _iprot.readString()
                    }
                    _got_success = true
                  }
                  case _ => TProtocolUtil.skip(_iprot, _field.`type`)
                }
              }
              case 1 => { /* te */
                _field.`type` match {
                  case TType.STRUCT => {
                    `te` = {
                      TimeoutException.decode(_iprot)
                    }
                    _got_te = true
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
        new Immutable(
          if (_got_success) Some(`success`) else None,
          if (_got_te) Some(`te`) else None
        )
      }
    }
  
    /**
     * The default read-only implementation of ping_result.  You typically should not need to
     * directly reference this class; instead, use the ping_result.apply method to construct
     * new instances.
     */
    class Immutable(
      val `success`: Option[String] = None,
      val `te`: Option[TimeoutException] = None
    ) extends ping_result
  
  }
  
  trait ping_result extends ThriftStruct
    with Product2[Option[String], Option[TimeoutException]]
    with java.io.Serializable
  {
    import ping_result._
  
    def `success`: Option[String]
    def `te`: Option[TimeoutException]
  
    def _1 = `success`
    def _2 = `te`
  
    override def write(_oprot: TProtocol) {
      validate()
      _oprot.writeStructBegin(Struct)
      if (`success`.isDefined) {
        val `success_item` = `success`.get
        _oprot.writeFieldBegin(SuccessField)
        _oprot.writeString(`success_item`)
        _oprot.writeFieldEnd()
      }
      if (`te`.isDefined) {
        val `te_item` = `te`.get
        _oprot.writeFieldBegin(TeField)
        `te_item`.write(_oprot)
        _oprot.writeFieldEnd()
      }
      _oprot.writeFieldStop()
      _oprot.writeStructEnd()
    }
  
    def copy(
      `success`: Option[String] = this.`success`,
      `te`: Option[TimeoutException] = this.`te`
    ): ping_result = new Immutable(
      `success`,
      `te`
    )
  
    /**
     * Checks that all required fields are non-null.
     */
    def validate() {
    }
  
    def canEqual(other: Any) = other.isInstanceOf[ping_result]
  
    override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)
  
    override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)
  
    override def toString: String = runtime.ScalaRunTime._toString(this)
  
    override def productArity = 2
  
    override def productElement(n: Int): Any = n match {
      case 0 => `success`
      case 1 => `te`
      case _ => throw new IndexOutOfBoundsException(n.toString)
    }
  
    override def productPrefix = "ping_result"
  }
  object recPosts_args extends ThriftStructCodec[recPosts_args] {
    val Struct = new TStruct("recPosts_args")
    val UserIdField = new TField("userId", TType.I64, 1)
  
    def encode(_item: recPosts_args, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = Immutable.decode(_iprot)
  
    def apply(_iprot: TProtocol): recPosts_args = decode(_iprot)
  
    def apply(
      `userId`: Long
    ): recPosts_args = new Immutable(
      `userId`
    )
  
    def unapply(_item: recPosts_args): Option[Long] = Some(_item.userId)
  
    object Immutable extends ThriftStructCodec[recPosts_args] {
      def encode(_item: recPosts_args, _oproto: TProtocol) { _item.write(_oproto) }
      def decode(_iprot: TProtocol) = {
        var `userId`: Long = 0L
        var _got_userId = false
        var _done = false
        _iprot.readStructBegin()
        while (!_done) {
          val _field = _iprot.readFieldBegin()
          if (_field.`type` == TType.STOP) {
            _done = true
          } else {
            _field.id match {
              case 1 => { /* userId */
                _field.`type` match {
                  case TType.I64 => {
                    `userId` = {
                      _iprot.readI64()
                    }
                    _got_userId = true
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
        if (!_got_userId) throw new TProtocolException("Required field 'userId' was not found in serialized data for struct recPosts_args")
        new Immutable(
          `userId`
        )
      }
    }
  
    /**
     * The default read-only implementation of recPosts_args.  You typically should not need to
     * directly reference this class; instead, use the recPosts_args.apply method to construct
     * new instances.
     */
    class Immutable(
      val `userId`: Long
    ) extends recPosts_args
  
  }
  
  trait recPosts_args extends ThriftStruct
    with Product1[Long]
    with java.io.Serializable
  {
    import recPosts_args._
  
    def `userId`: Long
  
    def _1 = `userId`
  
    override def write(_oprot: TProtocol) {
      validate()
      _oprot.writeStructBegin(Struct)
      if (true) {
        val `userId_item` = `userId`
        _oprot.writeFieldBegin(UserIdField)
        _oprot.writeI64(`userId_item`)
        _oprot.writeFieldEnd()
      }
      _oprot.writeFieldStop()
      _oprot.writeStructEnd()
    }
  
    def copy(
      `userId`: Long = this.`userId`
    ): recPosts_args = new Immutable(
      `userId`
    )
  
    /**
     * Checks that all required fields are non-null.
     */
    def validate() {
    }
  
    def canEqual(other: Any) = other.isInstanceOf[recPosts_args]
  
    override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)
  
    override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)
  
    override def toString: String = runtime.ScalaRunTime._toString(this)
  
    override def productArity = 1
  
    override def productElement(n: Int): Any = n match {
      case 0 => `userId`
      case _ => throw new IndexOutOfBoundsException(n.toString)
    }
  
    override def productPrefix = "recPosts_args"
  }
  object recPosts_result extends ThriftStructCodec[recPosts_result] {
    val Struct = new TStruct("recPosts_result")
    val SuccessField = new TField("success", TType.STRUCT, 0)
    val NfeField = new TField("nfe", TType.STRUCT, 1)
    val EeField = new TField("ee", TType.STRUCT, 2)
    val TeField = new TField("te", TType.STRUCT, 3)
  
    def encode(_item: recPosts_result, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = Immutable.decode(_iprot)
  
    def apply(_iprot: TProtocol): recPosts_result = decode(_iprot)
  
    def apply(
      `success`: Option[PostList] = None,
      `nfe`: Option[NotFoundException] = None,
      `ee`: Option[EngineException] = None,
      `te`: Option[TimeoutException] = None
    ): recPosts_result = new Immutable(
      `success`,
      `nfe`,
      `ee`,
      `te`
    )
  
    def unapply(_item: recPosts_result): Option[Product4[Option[PostList], Option[NotFoundException], Option[EngineException], Option[TimeoutException]]] = Some(_item)
  
    object Immutable extends ThriftStructCodec[recPosts_result] {
      def encode(_item: recPosts_result, _oproto: TProtocol) { _item.write(_oproto) }
      def decode(_iprot: TProtocol) = {
        var `success`: PostList = null
        var _got_success = false
        var `nfe`: NotFoundException = null
        var _got_nfe = false
        var `ee`: EngineException = null
        var _got_ee = false
        var `te`: TimeoutException = null
        var _got_te = false
        var _done = false
        _iprot.readStructBegin()
        while (!_done) {
          val _field = _iprot.readFieldBegin()
          if (_field.`type` == TType.STOP) {
            _done = true
          } else {
            _field.id match {
              case 0 => { /* success */
                _field.`type` match {
                  case TType.STRUCT => {
                    `success` = {
                      PostList.decode(_iprot)
                    }
                    _got_success = true
                  }
                  case _ => TProtocolUtil.skip(_iprot, _field.`type`)
                }
              }
              case 1 => { /* nfe */
                _field.`type` match {
                  case TType.STRUCT => {
                    `nfe` = {
                      NotFoundException.decode(_iprot)
                    }
                    _got_nfe = true
                  }
                  case _ => TProtocolUtil.skip(_iprot, _field.`type`)
                }
              }
              case 2 => { /* ee */
                _field.`type` match {
                  case TType.STRUCT => {
                    `ee` = {
                      EngineException.decode(_iprot)
                    }
                    _got_ee = true
                  }
                  case _ => TProtocolUtil.skip(_iprot, _field.`type`)
                }
              }
              case 3 => { /* te */
                _field.`type` match {
                  case TType.STRUCT => {
                    `te` = {
                      TimeoutException.decode(_iprot)
                    }
                    _got_te = true
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
        new Immutable(
          if (_got_success) Some(`success`) else None,
          if (_got_nfe) Some(`nfe`) else None,
          if (_got_ee) Some(`ee`) else None,
          if (_got_te) Some(`te`) else None
        )
      }
    }
  
    /**
     * The default read-only implementation of recPosts_result.  You typically should not need to
     * directly reference this class; instead, use the recPosts_result.apply method to construct
     * new instances.
     */
    class Immutable(
      val `success`: Option[PostList] = None,
      val `nfe`: Option[NotFoundException] = None,
      val `ee`: Option[EngineException] = None,
      val `te`: Option[TimeoutException] = None
    ) extends recPosts_result
  
  }
  
  trait recPosts_result extends ThriftStruct
    with Product4[Option[PostList], Option[NotFoundException], Option[EngineException], Option[TimeoutException]]
    with java.io.Serializable
  {
    import recPosts_result._
  
    def `success`: Option[PostList]
    def `nfe`: Option[NotFoundException]
    def `ee`: Option[EngineException]
    def `te`: Option[TimeoutException]
  
    def _1 = `success`
    def _2 = `nfe`
    def _3 = `ee`
    def _4 = `te`
  
    override def write(_oprot: TProtocol) {
      validate()
      _oprot.writeStructBegin(Struct)
      if (`success`.isDefined) {
        val `success_item` = `success`.get
        _oprot.writeFieldBegin(SuccessField)
        `success_item`.write(_oprot)
        _oprot.writeFieldEnd()
      }
      if (`nfe`.isDefined) {
        val `nfe_item` = `nfe`.get
        _oprot.writeFieldBegin(NfeField)
        `nfe_item`.write(_oprot)
        _oprot.writeFieldEnd()
      }
      if (`ee`.isDefined) {
        val `ee_item` = `ee`.get
        _oprot.writeFieldBegin(EeField)
        `ee_item`.write(_oprot)
        _oprot.writeFieldEnd()
      }
      if (`te`.isDefined) {
        val `te_item` = `te`.get
        _oprot.writeFieldBegin(TeField)
        `te_item`.write(_oprot)
        _oprot.writeFieldEnd()
      }
      _oprot.writeFieldStop()
      _oprot.writeStructEnd()
    }
  
    def copy(
      `success`: Option[PostList] = this.`success`,
      `nfe`: Option[NotFoundException] = this.`nfe`,
      `ee`: Option[EngineException] = this.`ee`,
      `te`: Option[TimeoutException] = this.`te`
    ): recPosts_result = new Immutable(
      `success`,
      `nfe`,
      `ee`,
      `te`
    )
  
    /**
     * Checks that all required fields are non-null.
     */
    def validate() {
    }
  
    def canEqual(other: Any) = other.isInstanceOf[recPosts_result]
  
    override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)
  
    override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)
  
    override def toString: String = runtime.ScalaRunTime._toString(this)
  
    override def productArity = 4
  
    override def productElement(n: Int): Any = n match {
      case 0 => `success`
      case 1 => `nfe`
      case 2 => `ee`
      case 3 => `te`
      case _ => throw new IndexOutOfBoundsException(n.toString)
    }
  
    override def productPrefix = "recPosts_result"
  }
}