package org.imooc.controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.imooc.constant.ApiCodeEnum;
import org.imooc.dto.AdDto;
import org.imooc.dto.ApiCodeDto;
import org.imooc.dto.BusinessDto;
import org.imooc.dto.BusinessListDto;
import org.imooc.dto.CommentForSubmitDto;
import org.imooc.dto.CommentListDto;
import org.imooc.dto.OrderForBuyDto;
import org.imooc.dto.OrdersDto;
import org.imooc.service.AdService;
import org.imooc.service.CommentService;
import org.imooc.service.MemberService;
import org.imooc.service.OrdersService;
import org.imooc.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//RestController=responseBody+controller
@RestController
@RequestMapping("/api")
public class ApiController {
	@Resource
	private MemberService memberService;
    @Autowired
	private AdService adService;
	@Resource
	private OrdersService ordersService;
	@Resource
	private CommentService commentService;
	/**
	 * 首页 —— 广告（超值特惠）
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	
	@RequestMapping(value = "/homead", method = RequestMethod.GET)
	public List<AdDto> homead(){
		//ObjectMapper mapper = new ObjectMapper();
		//String s = "[{\"title\":\"暑假5折\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191639092-2000037796.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"特价出国\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191648124-298129318.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"亮亮车\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191653983-1962772127.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"学钢琴\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191700420-1584459466.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"电影\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191706733-367929553.png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"旅游热线\",\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191713186-495002222.png\",\"link\":\"http://www.imooc.com/wap/index\"}]";
		
		//return mapper.readValue(s,new TypeReference<List<AdDto>>() {});
		AdDto adDto = new AdDto();
		adDto.getPage().setPageNumber(6);
		return adService.searchByPage(adDto);
	}

	/**
	 * 首页 —— 推荐列表（猜你喜欢）
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/homelist/{city}/{page.currentPage}", method = RequestMethod.GET)
	public BusinessListDto homelist(BusinessDto businessDto) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String s = "{\"hasMore\":true,\"data\":[{\"img\":\"http://g.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=d852ae3f8494a4c20a76ef2f3bc437e3/e4dde71190ef76c6a935b41b9e16fdfaaf51671e.jpg\",\"title\":\"汉堡大大11\",\"subTitle\":\"叫我汉堡大大，还你多彩口味\",\"price\":\"28\",\"distance\":\"120m\",\"mumber\":\"389\",\"id\":\"6261170837131391\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201645858-1342445625.png\",\"title\":\"北京开源饭店\",\"subTitle\":\"[望京]自助晚餐\",\"price\":\"98\",\"distance\":\"140m\",\"mumber\":\"689\",\"id\":\"6426906644915766\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201652952-1050532278.png\",\"title\":\"服装定制\",\"subTitle\":\"原价xx元，现价xx元，可修改一次\",\"price\":\"1980\",\"distance\":\"160\",\"mumber\":\"106\",\"id\":\"5749145392303552\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201700186-1351787273.png\",\"title\":\"婚纱摄影\",\"subTitle\":\"免费试穿，拍照留念\",\"price\":\"2899\",\"distance\":\"160\",\"mumber\":\"58\",\"id\":\"3838329886957028\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201708124-1116595594.png\",\"title\":\"麻辣串串烧\",\"subTitle\":\"双人免费套餐等你抢购\",\"price\":\"0\",\"distance\":\"160\",\"mumber\":\"1426\",\"id\":\"9499467197483056\"}]}";
		return mapper.readValue(s,new TypeReference<BusinessListDto>() {});
		//businessDto.getPage().setPageNumber(businessHomeNumber);
		//return businessService.searchByPageForApi(businessDto);
	}

	/**
	 * 搜索结果页 - 搜索结果 - 三个参数
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/search/{page.currentPage}/{city}/{category}/{keyword}", method = RequestMethod.GET)
	public BusinessListDto searchByKeyword(BusinessDto businessDto) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String s = "{\"hasMore\":true,\"data\":[{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022145742279-606202974.jpg\",\"title\":\"河束人家\",\"subTitle\":\"南锣鼓巷店\",\"price\":\"150\",\"distance\":\"120m\",\"mumber\":\"389\",\"id\":\"16414190508477233\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022145750123-1745839503.jpg\",\"title\":\"漫漫火锅\",\"subTitle\":\"[王府井]自助火锅\",\"price\":\"113\",\"distance\":\"140m\",\"mumber\":\"689\",\"id\":\"10379361046633062\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022145755545-1770557408.jpg\",\"title\":\"北方涮肉\",\"subTitle\":\"什刹海店\",\"price\":\"92\",\"distance\":\"160\",\"mumber\":\"106\",\"id\":\"2684953608146943\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022145800732-576947550.jpg\",\"title\":\"姓高火锅\",\"subTitle\":\"知春里店\",\"price\":\"90\",\"distance\":\"160\",\"mumber\":\"58\",\"id\":\"4327667029608906\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022145806201-1193851669.jpg\",\"title\":\"八重牛府\",\"subTitle\":\"最好吃的牛丸\",\"price\":\"85\",\"distance\":\"160\",\"mumber\":\"1426\",\"id\":\"05417345080828273\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022150855185-1659375763.jpg\",\"title\":\"蜀乡涮锅\",\"subTitle\":\"[王府井]自助火锅\",\"price\":\"113\",\"distance\":\"140m\",\"mumber\":\"689\",\"id\":\"4165909692594816\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022145800732-576947550.jpg\",\"title\":\"满楼福火锅\",\"subTitle\":\"知春路店\",\"price\":\"90\",\"distance\":\"160\",\"mumber\":\"58\",\"id\":\"17548314689876077\"}]}";
		return mapper.readValue(s,new TypeReference<BusinessListDto>() {});
	}

	/**
	 * 搜索结果页 - 搜索结果 - 两个参数
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/search/{page.currentPage}/{city}/{category}", method = RequestMethod.GET)
	public BusinessListDto search(BusinessDto businessDto) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String s = "{\"hasMore\":true,\"data\":[{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022145742279-606202974.jpg\",\"title\":\"河束人家\",\"subTitle\":\"南锣鼓巷店\",\"price\":\"150\",\"distance\":\"120m\",\"mumber\":\"389\",\"id\":\"16414190508477233\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022145750123-1745839503.jpg\",\"title\":\"漫漫火锅\",\"subTitle\":\"[王府井]自助火锅\",\"price\":\"113\",\"distance\":\"140m\",\"mumber\":\"689\",\"id\":\"10379361046633062\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022145755545-1770557408.jpg\",\"title\":\"北方涮肉\",\"subTitle\":\"什刹海店\",\"price\":\"92\",\"distance\":\"160\",\"mumber\":\"106\",\"id\":\"2684953608146943\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022145800732-576947550.jpg\",\"title\":\"姓高火锅\",\"subTitle\":\"知春里店\",\"price\":\"90\",\"distance\":\"160\",\"mumber\":\"58\",\"id\":\"4327667029608906\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022145806201-1193851669.jpg\",\"title\":\"八重牛府\",\"subTitle\":\"最好吃的牛丸\",\"price\":\"85\",\"distance\":\"160\",\"mumber\":\"1426\",\"id\":\"05417345080828273\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022150855185-1659375763.jpg\",\"title\":\"蜀乡涮锅\",\"subTitle\":\"[王府井]自助火锅\",\"price\":\"113\",\"distance\":\"140m\",\"mumber\":\"689\",\"id\":\"4165909692594816\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161022145800732-576947550.jpg\",\"title\":\"满楼福火锅\",\"subTitle\":\"知春路店\",\"price\":\"90\",\"distance\":\"160\",\"mumber\":\"58\",\"id\":\"17548314689876077\"}]}";
		return mapper.readValue(s,new TypeReference<BusinessListDto>() {});
	}

	/**
	 * 详情页 - 商户信息
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/detail/info/{id}", method = RequestMethod.GET)
	public BusinessDto detail(@PathVariable("id") Long id) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String s = "{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201645858-1342445625.png\",\"title\":\"天下第一锅\",\"star\":4,\"price\":\"88\",\"subTitle\":\"重庆 & 四川 麻辣火锅\",\"desc\":\"营业时间 11:00 - 21:00 <br> 电话订购 11:00 - 19:00 <br> 网络订购 11:00 - 19:00\"}";
		return mapper.readValue(s,new TypeReference<BusinessDto>() {});
	}

	/**
	 * 提交评论
	 */
	@RequestMapping(value = "/submitComment", method = RequestMethod.POST)
	public ApiCodeDto submitComment(CommentForSubmitDto dto) {
		ApiCodeDto result;
		// TODO 需要完成的步骤：
		// 1、校验登录信息：token、手机号
		Long phone = memberService.getPhone(dto.getToken());
		if (phone != null && phone.equals(dto.getUsername())) {
			// 2、根据手机号取出会员ID
			Long memberId = memberService.getIdByPhone(phone);
			// 3、根据提交上来的订单ID获取对应的会员ID，校验与当前登录的会员是否一致
			OrdersDto ordersDto = ordersService.getById(dto.getId());
			if(ordersDto.getMemberId().equals(memberId)) {
				// 4、保存评论
				commentService.add(dto);
				result = new ApiCodeDto(ApiCodeEnum.SUCCESS);
				// TODO
				// 5、还有一件重要的事未做
			} else {
				result = new ApiCodeDto(ApiCodeEnum.NO_AUTH);
			}
		} else {
			result = new ApiCodeDto(ApiCodeEnum.NOT_LOGGED);
		}
		return result;
	}

	/**
	 * 订单列表
	 */
	@RequestMapping(value = "/orderlist/{username}", method = RequestMethod.GET)
	public List<OrdersDto> orderlist() throws JsonParseException, JsonMappingException, IOException {
		String s = "[{\"id\":1494060890936,\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201638030-473660627.png\",\"title\":\"汉堡大王11\",\"count\":3,\"price\":\"167\",\"commentState\":0},{\"id\":1494060890936,\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201708124-1116595594.png\",\"title\":\"麻辣香锅\",\"count\":1,\"price\":\"188\",\"commentState\":0},{\"id\":1494060890936,\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201645858-1342445625.png\",\"title\":\"好吃自出餐\",\"count\":2,\"price\":\"110\",\"commentState\":2}]";
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(s, new TypeReference<List<OrdersDto>>() {});
	}

	/**
	 * 提交评论
	 
	
	@RequestMapping(value = "/submitComment", method = RequestMethod.POST)
	public Map<String, Object> submitComment() {
		Map<String, Object> result = new HashMap<>();
		result.put("errno", 0);
		result.put("msg", "ok");
		return result;
	}
	*/
	/**
	 * 根据手机号下发短信验证码
	 */
	@RequestMapping(value = "/sms", method = RequestMethod.POST)
	public ApiCodeDto sms(@RequestParam("username") Long username) {
		ApiCodeDto dto;
		// 1、验证用户手机号是否存在（是否注册过）
		if (memberService.exists(username)) {
			// 2、生成6位随机数
			String code = String.valueOf(CommonUtil.random(6));
			// 3、保存手机号与对应的md5(6位随机数)（一般保存1分钟，1分钟后失效）
			if (memberService.saveCode(username, code)) {
				// 4、调用短信通道，将明文6位随机数发送到对应的手机上。
				if (memberService.sendCode(username, code)) {
					dto = new ApiCodeDto(ApiCodeEnum.SUCCESS.getErrno(), code);
				} else {
					dto = new ApiCodeDto(ApiCodeEnum.SEND_FAIL);
				}
			} else {
				dto = new ApiCodeDto(ApiCodeEnum.REPEAT_REQUEST);
			}
		} else {
			dto = new ApiCodeDto(ApiCodeEnum.USER_NOT_EXISTS);
		}
		
		return dto;
	}
	/**
	 * 会员登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ApiCodeDto login(@RequestParam("username") Long username, @RequestParam("code") String code) {
		ApiCodeDto dto;
		// 1、用手机号取出保存的md5(6位随机数)，能取到，并且与提交上来的code值相同为校验通过
		String saveCode = memberService.getCode(username);//根据key取values
		if (saveCode != null) {
			if (saveCode.equals(code)) {
				// 2、如果校验通过，生成一个32位的token
				String token = CommonUtil.getUUID();
				// 3、保存手机号与对应的token（一般这个手机号中途没有与服务端交互的情况下，保持10分钟）
				memberService.saveToken(token, username);
				// 4、将这个token返回给前端
				dto = new ApiCodeDto(ApiCodeEnum.SUCCESS);
				dto.setToken(token);
			} else {
				dto = new ApiCodeDto(ApiCodeEnum.CODE_ERROR);
			}
		} else {
			dto = new ApiCodeDto(ApiCodeEnum.CODE_INVALID);
		}
		return dto;
	}
	@RequestMapping(value="/order",method = RequestMethod.POST)
	public ApiCodeDto order(OrderForBuyDto orderForBuyDto){
		ApiCodeDto dto;
		Long phone = memberService.getPhone(orderForBuyDto.getToken());
		if(phone != null && phone.equals(orderForBuyDto.getUsername())){
			//get id
			Long memberId = memberService.getIdByPhone(phone);
			OrdersDto ordersDto = new OrdersDto();
			ordersDto.setNum(orderForBuyDto.getNum());
			ordersDto.setPrice(orderForBuyDto.getPrice());
			ordersDto.setBusinessId(orderForBuyDto.getId());
			ordersDto.setMemberId(memberId);
			ordersService.add(ordersDto);
			dto = new ApiCodeDto(ApiCodeEnum.SUCCESS);
		}else{
			dto = new ApiCodeDto(ApiCodeEnum.NOT_LOGGED);
		}
		return dto;
	}
}
