##本项目配置的springboot版本为2.X，顾redis的客户端默认lettuce，而不是jedis。


redis的应用场景
1、缓存（token，短信验证码等）
2、消息队列
    生产者消费者模式（高并发，只有一个消费者获得）、发布者订阅者模式（订阅者获取的数据一致）
    Redis 中list的数据结构实现是双向链表，所以可以非常便捷的应用于消息队列（生产者 / 消费者模型）。消息的生产者只需要通过lpush将消息放入 list，消费者便可以通过rpop取出该消息，并且可以保证消息的有序性。如果需要实现带有优先级的消息队列也可以选择sorted set。
    而pub/sub功能也可以用作发布者 / 订阅者模型的消息。无论使用何种方式，由于 Redis 拥有持久化功能，也不需要担心由于服务器故障导致消息丢失的情况。
    Redis List的主要操作为lpush/lpop/rpush/rpop四种，分别代表从头部和尾部的push/pop，除此之外List还提供了两种pop操作的阻塞版本blpop/brpop，用于阻塞获取一个对象。
3、排行榜(主播热度等)
    使用sorted set和一个计算热度的算法便可以轻松打造一个热度排行榜，zrevrangebyscore可以得到以分数倒序排列的序列，zrank可以得到一个成员在该排行榜的位置（是分数正序排列时的位置，如果要获取倒序排列时的位置需要用zcard-zrank）。
4、时间轴（Timeline）
    redis的list不仅可以作为队列使用，也可以作为栈（先进后出）使用，获取最新的数据（即直接获取倒序顺序的数据）；
5、计数器（inc）
    计数功能应该是最适合 Redis 的使用场景之一了，因为它高频率读写的特征可以完全发挥 Redis 作为内存数据库的高效。在 Redis 的数据结构中，string、hash和sorted set都提供了incr方法用于原子性的自增操作
    1-如果应用需要显示每天的注册用户数，便可以使用string作为计数器，，并在设定一个名为REGISTERED_COUNT_TODAY的 key初始化时给它设置一个到凌晨 0 点的过期时间，每当用户注册成功后便使用incr命令使该 key 增长 1，同时当每天凌晨 0 点后，这个计数器都会因为 key 过期使值清零。
    2-每条微博都有点赞数、评论数、转发数和浏览数四条属性，这时用hash进行计数会更好，将该计数器的 key 设为weibo:weibo_id，hash的 field 为like_number、comment_number、forward_number和view_number，在对应操作后通过hincrby使hash 中的 field 自增。
    3-如果应用有一个发帖排行榜的功能，便选择sorted set吧，将集合的 key 设为POST_RANK。当用户发帖后，使用zincrby将该用户 id 的 score 增长 1。sorted set会重新进行排序，用户所在排行榜的位置也就会得到实时的更新。
6、好友关系（利用redis的快速计算集合中交集这个功能）
7、分布式锁（利用key set的原子性，增加有效时间）