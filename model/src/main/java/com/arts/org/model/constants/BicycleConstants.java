
package com.arts.org.model.constants;

public class BicycleConstants {

	// key前辍+phone,存储注册认证码
	public static final String REG_CODE = "AC_";

	// key前辍+phone,存储登陆验证码
	public static final String LOGIN_CODE = "LC_";
	
	// key前辍+phone,存储找回密码验证码
	public static final String RESETPWD_CODE = "RSP_";
	
	//变更绑定手机号验证码
	public static final String CHGPHONE_CODE = "CHGP_";

	// key前辍+phone,存储修改手机号验证码
	public static final String UPDATE_PHONE_CODE = "UP_";

	// 已注册用户的key,存储已注册用户set
	public static final String REGISTERUSER_SET_KEY = "REGP_KEY";

	// 获取新用户id的key,通过redis的incr方法获取新用户id
	public static final String USER_ID_KEY = "UID_KEY";
	
	// 获取新群组id的key,通过redis的incr方法获取新组id
	public static final String GROUP_ID_KEY = "GID_KEY";

	// key前辍+phone,根据phone获取uid
	public static final String PHONE_KEY = "PHUID_";

	// key前辍+uid,存储已注册用户明细
	public static final String REGISTERUSER_INFO_KEY = "REGU_";

	// key前缀+uid, 临时存储用户上传的通讯录用户set，求临时set和已注册用户set的交集
	public static final String TEMPUSER_SET_KEY = "TREGP_";

	// key前辍+msgid,存储动态消息缩略缓存hash(uid,msgid,content缩略,msgPics,createTime)
	public static final String MSG_INFO_KEY = "MSG_";
	
	//缩略消息内容字数
	public static final int SIMPLE_MSG_CONTENTLEN= 32;

	//key前辍+uid,捕梦禁言，String
	public static final String WOPAI_FORBID_WORD_KEY = "FBU_";

	//key前辍+uid,通讯录黑名单set
	public static final String MYCHAT_BLACKLIST_KEY = "CBU_";

	// key前辍+uid,在zset存储动态消息id
	public static final String MSG_ID_KEY = "MSGID_";

	// key前辍+uid,存储新鲜事消息id缓存列表
	public static final String FRMSG_ID_KEY = "FRMSGID_";
	
	// key前辍+phone,存储好友信息缓存列表
	public static final String ACTIVITY_KEY = "ACTY_";

	// key前辍+uid，在zset存储所有好友的uid
	public static final String FRIEND_KEY = "FRI_";
	
	// key前辍+uid，在map存储所有好友的备注名和是否新标联系人信息(friendid,json对象)
    public static final String FRIENDMAP_KEY = "FRIM_";
    
    public static final String FRIENDREMARK_KEY = "FRIMZ_";
	
	//session key
	public static final String SES_KEY = "SES_";

	//key前辍+uid,存储离线消息hash(mid:消息jsonStr)
	public static final String OFFLINE_KEY = "OFFLINE_";
	
	//key前辍+uid,存储离线消息list(消息jsonStr)
    public static final String OFFLINE_LIST_KEY = "OFL_";
    //好友推荐离线,存储list
    public static final String OFFLINE_RECOMMEND_KEY = "OFL_{0}_{1}";

	//key前辍+uid,存储已推送消息数量 key:String
	public static final String PUSHED_KEY = "PUSHED_";
	
	//key前辍+uid,苹果用户已收取消息id的set
	public static final String RECEIVED_KEY = "GOT_";
	
	//排序id
	public static final String SMILEFACE_ID="smfid";

	//key前辍+uid 收藏id前缀
	public static final String FAVID_KEY = "FAVID_";

	//key前辍+uid 赞消息前缀
	public static final String PRAMSGID_KEY = "PRAMSGID_";

	//用户收藏动态消息的数量  
	public static final int FAVMSG_COUNT = 50;

	//用户最近发布动态消息图片
	public static final String RECENT_PICS = "RPIC_";
	
	//用户最近发布动态消息缩略图
	public static final String RECENT_MINIPICS = "RMIPIC_";
	
	//用户最近发布动态消息图片列表
	public static final String RECENT_PIC_LIST = "RPICL_";

	//用户最近发布图片缓存数
	public static final int RECENT_PICS_COUNT = 3;

	// uid+分隔符+msgid,用于新鲜事value存值分隔
	public static final String UID_MSGID_SPLIT = "_";

	// 用户初始id
	public static final String USER_ID_INIT = "100000";

	// 用户表字段msgTotal
	public static final String MSG_TOTAL_FIELD = "msgTotal";

	// 用户状态
	public static final int USER_STATUS_NOACTIVE = 0;
	public static final int USER_STATUS_ACTIVE = 1;
	public static final int USER_STATUS_DISABLED = 2;

	// 每页记录数
	public static final int PAGE_SIZE = 10;

	// 用户动态消息摘要在hash中缓存的数量
	public static final int SIMPLEMSG_LIST_SIZE = 10;

	//用户自己的动态消息在list中缓存的msgid数量
	public static final int MSG_LIST_SIZE = 30;

	//用户新鲜事在list中缓存的msgid数量
	public static final int FRIENDMSG_LIST_SIZE = 30;

	// 动态消息id分隔符 uid+分隔符+msgNum
	public static final String MSG_ID_SPLIT = "-";
	
	//tokenid前缀
	public static final String TOKENID_KEY = "TOK_";
	
	public static final String ADDFRIEND_KEY = "ADDF_{0}_{1}";
	

	// 短信类型
	public static final int SMS_TYPE_REG = 1;
	public static final int SMS_TYPE_LOGIN = 2;
	public static final int SMS_TYPE_UPDATEPHONE = 3;
	public static final int SMS_TYPE_RESETPWD = 4;
	public static final int SMS_TYPE_ONEKEYREG = 5;
	public static final int SMS_TYPE_CHGPHONE = 6;

	// 用户关系表type
	public static final int M_USER_RELATION_TYPE = 1;

	// 0:主帖 1：评论
	public static final int MSG_TYPE_PUBLISH = 0;
	public static final int MSG_TYPE_REPLY = 1;

	// 0:赞 1：取消赞 2:已赞 3：已取消赞
	public static final int MSG_PRAISE_INSERT = 0;
	public static final int MSG_PRAISE_CANCLE = 1;
	public static final int MSG_PRAISE_INSERTED = 2;
	public static final int MSG_PRAISE_CANCLED = 3;
	
	//是否在黑名单列表  0:不在 1:在
	public static final int NOTIN_BLACKLIST = 0;
	public static final int IN_BLACKLIST = 1;
	
	//更新个人头像或签名时，发布的动态消息内容 
	public static final String UPDATE_HEADPIC_CONTENT = "更新了头像";
	public static final String UPDATE_SIGNATRUE_CONTENT = "更新了签名";
	
	//图片持久化或非持久化路径
	public static final String PERSISTENCE_IMG_PATH = "img/";
	public static final String TEMP_PIC_PATH = "pic/";
	
	//苹果osVersion 前缀
	public static final String APPLE_OSVERSION_PREX = "ios_";
	
	//分页标识0：第一页，1：向下翻页（取最新数据），2：向上翻页（取历史数据））
	public static final int PAGEFLAG_FRISTPAGE = 0;
	public static final int PAGEFLAG_DOWNPAGE = 1;
	public static final int PAGEFLAG_UPPAGE = 2;
	
	//表情包状态
	public static final int PACKAGE_STATUS_ONLINE = 1;
	public static final int PACKAGE_STATUS_OFFLINE = 2;
	
	//0：不清除缓存   1:清除缓存
	public static final int NO_CLEAR_CACHE = 0; 
	public static final int CLEAR_CACHE = 1;
	//清除缓存要达到的删除记录数
	public static final int CLEAR_CACHE_SIZE =5;
	
	/**
	 * SES_$phone内容中存在用户sessionid的key
	 */
	public static final String USER_SESSIONID = "sessionid";
	
	/**
	 * 客户端请求时request header中携带的sessionid名称
	 */
	public static final String REQUEST_HEADER_SESSION_NAME = "sessionid";
	
	/**
	 * 等待回执的消息 
	 * --------------------------------------
	 * | KEY1       |  KEY2      |   VALUE  |
	 * --------------------------------------
	 * | RECM_$uid  | message_id |  content |
	 * --------------------------------------
	 */
	public static final String MESSAGE_RECEIPTS_WAITING_KEY = "MSGREC_";
	
	public static final String ANDROID_CLIENT_VERSON = "ANDROID_VERSION";
	//android上一次强制升级的key
	public static final String ANDROID_CLIENT_VERSON_PRE = "ANDROID_VERSION_PREV";
	public static final String IOS_CLIENT_VERSON = "IOS_VERSION";
	//IOS上一次强制升级的key
	public static final String IOS_CLIENT_VERSON_PRE = "IOS_VERSION_PREV";
	public static final String ANDROID_CLIENT_OS = "android";
	public static final String IOS_CLIENT_OS = "ios";
	public static final int CLIENT_UPGRADE_TYPE_NOTHING = -1;
	public static final int CLIENT_UPGRADE_TYPE_IGONRE = 0;
	public static final int CLIENT_UPGRADE_TYPE_TIP = 1;
	public static final int CLIENT_UPGRADE_TYPE_FORCE = 2;
	
	//用户一天内重置密码次数 到期key失效
	public static final String USER_REST_PWD_COUNT = "URPC_";
	//用户一天内找回密码次数 到期key失效
	public static final String USER_FORGET_PWD_COUNT = "UFPC_";
	//注册时发短信次数
	public static final String USER_REG_DX_COUNT = "URDXC_";
	//修改绑定手机号发送短信次数
	public static final String USER_CHGP_DX_COUNT = "CHGPC_";
	
	//是否生成缩略图片
	public static final int GENERATE_MINIPIC = 1;
	public static final int NOGENERATE_MINIPIC = 0;
	
	/**
	 * 群成员redis key 前缀
	 */
	public static final String GROUP_MEMBER_KEY = "GMEM_";
	public static final String GROUP_KEY = "GROU_";
	
	//key前缀+uid，存储某用户关注的人的id（set）
	public static final String ATTENTION_ID_OF = "ATTENTION_ID_OF_";
	
	//存储蓝v用户id（set）
	public static final String VIP_OF_BLUE = "VIP_OF_BLUE";
	
	//存储黄v用户id（set）
	public static final String VIP_OF_YELLOW = "VIP_OF_YELLOW";

	//存储新注册用户id（set）
	public static final String NEW_REGISTER_USER = "NEW_REGISTER_USER";
	
	//存储马甲用户id（set）
	public static final String MAJIA_UID = "MAJIA_UID";
	
	//key前缀+uid，存储马甲用户信息（map）
	public static final String MAJIA = "MAJIA_";

	//key前缀+uid，存储用户信息（map）
	public static final String USER_INFO = "USER_INFO_";
	
	//存储积分总数 key前缀+uid
	public static final String SION_SUM_BYDAY = "SION_SUM_BYDAY_";
	
	//新用户标识
    public static final String NEW_USER_FLAG = "NEW_USER_FLAG_";
	
	
	//存储热度排行榜视频(set)，每个视频对象以json串的方式存储
	public static final String HOT_VIDEO = "HOT_VIDEO";
	
	//key前缀+活动id，存储某个活动热度排行榜视频(set)，每个视频对象以json串的方式存储
	public static final String HOT_ACTIVITY_VIDEO = "HOT_ACTIVITY_VIDEO_";
	
	//存储所有视频热度权重（map）
	public static final String HOT_WEIGHT_OF_ALL = "HOT_WEIGHT_OF_ALL";
	
	//存储活动视频热度权重（map）
	public static final String HOT_WEIGHT_OF_ACTIVITY = "HOT_WEIGHT_OF_ACTIVITY";
	
	//点赞权重(map中的field)
	public static final String PRIASE_WEIGHT = "praise_weight";
	//评论时间权重(map中的field)
	public static final String EVALUATION_WEIGHT = "evaluation_weight";
	//发布时间权重(map中的field)
	public static final String PUBLISH_WEIGHT = "publish_weight";
	//播放次数权重(map中的field)
	public static final String PLAY_WEIGHT = "play_weight";
	
	//当日最热视频榜权重（map）
	public static final String DAY_HOT_VIDEOS_WEIGHT = "DAY_HOT_VIDEOS_WEIGHT";
	
	//当日播放次数权重(map中的field)
	public static final String DAY_PLAYCOUNT_WEIGHT = "day_playcount_weight";
	//当日点赞次数权重(map中的field)
	public static final String DAY_PRAISECOUNT_WEIGHT = "day_praisecount_weight";
	//当日评论次数权重(map中的field)
	public static final String DAY_EVALUATIONCOUNT_WEIGHT = "day_evaluationcount_weight";
		
	//当日最热视频榜权重（map）
	public static final String DAY_POPULARITY_WEIGHT = "DAY_POPULARITY_WEIGHT";
	
	//当日播放次数权重(map中的field)
	public static final String DAY_VIDEOCOUNT_WEIGHT = "day_videocount_weight";
	//当日点赞次数权重(map中的field)
	public static final String DAY_FANSCOUNT_WEIGHT = "day_fanscount_weight";
	
	//新浪token
	public static final String SINA_TOKEN = "sina_token";
	
	//key 当日最热视频排行，序列化对象存入
	public static final String DAY_HOT_VIDEOS = "DAY_HOT_VIDEOS";
	
	//key 当日用户人气排行榜，序列化对象存入
	public static final String DAY_USER_POPULARITY = "DAY_USER_POPULARITY";
	
	//捕梦小助手id，由于注册默认关注
	public static final String OFFICICAL_USER_ID="OFFICICAL_USER_ID";
	
	
	//邀请信息标示
	public static final String INVITE_INFO_WEIXIN_MARK="INVITE_INFO_WEIXIN_MARK";//微信好友
	public static final String INVITE_INFO_QZONE_MARK="INVITE_INFO_QZONE_MARK";//qq空间
	public static final String INVITE_INFO_ADDRESS_MARK="INVITE_INFO_ADDRESS_MARK";//手机通讯录
	public static final String INVITE_INFO_QQ_MARK="INVITE_INFO_QQ_MARK";//qq好友
	public static final String INVITE_INFO_SINA_MARK="INVITE_INFO_SINA_MARK";//新浪
	public static final String INVITE_INFO_WEIXIN_TIMELINE_MARK="INVITE_INFO_WEIXIN_TIMELINE_MARK";//微信朋友圈
		
	//所有的url的redis存储的key
	public static final String ALL_URL_CONFIG_REDIS_KEY="ALL_URL_CONFIG_REDIS_KEY";
	
	public static final String QIAN_DAO_GET_STR="签到";//签到获取
	public static final String FEN_XIANG_GET_STR="分享";//分享获取
	public static final int QIAN_DAO_GET=1;//签到获取
	public static final int FEN_XIANG_GET=2;//分享获取
	public static final int FEN_XIANG_GET_SIGN=100;
	//url设置类型
	public static final String URL_CONFIG_VIDEO_CDN="cdnurl";
	public static final String URL_CONFIG_VOICE="voiceurl";
	public static final String URL_CONFIG_IMAGE="imageurl";
	public static final String URL_CONFIG_INTERFACE="interfaceurl";
	public static final String URL_CONFIG_VIDEO_UPLOADURL="videouploadurl";
}