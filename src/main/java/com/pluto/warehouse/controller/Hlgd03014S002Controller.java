//package jp.co.benefitone.hlgd.counseling2.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import jp.co.benefitone.hlgd.common.constant.MappingConstant;
//import jp.co.benefitone.hlgd.common.exception.BusinessException;
//import jp.co.benefitone.hlgd.common.type.CodeEnum;
//import jp.co.benefitone.hlgd.common.type.DeclineReasonType;
//import jp.co.benefitone.hlgd.common.utils.RedirectUtil;
//import jp.co.benefitone.hlgd.common.utils.SessionUtil;
//import jp.co.benefitone.hlgd.counseling2.constants.Constant;
//import jp.co.benefitone.hlgd.counseling2.dto.Hlgd03014S002DisplayDto;
//import jp.co.benefitone.hlgd.counseling2.form.Hlgd03014S002Form;
//import jp.co.benefitone.hlgd.counseling2.repository.Hlgd03014S002DeclineInterviewInfoDtoIn;
//import jp.co.benefitone.hlgd.counseling2.service.Hlgd03014S002Service;
//
///**
// * 面談予約状況確認画面コントローラー.
// * 
// * @author Benefit-One Corp.
// *
// */
//@Controller
//@RequestMapping(MappingConstant.URI_COUNSELING2)
//@SessionAttributes(Constant.DISPLAYDTO_HLGD03014S002)
//public class Hlgd03014S002Controller {
//
////    /** バンディングキー */
////    private static final String BINDING_KEY = CommonUtil.getBindingResultKey(Constant.FORM_HLGD03014S002);
//    /** 対象者ID */
//    private static final String TARGET_PERSON_ID = "targetPersonId";
//    /** イベントID */
//    private static final String EVENT_ID = "eventId";
//    /** ステータス */
//    private static final String STATUS = "status";
//    /** ステータス値 */
//    private static final String STATUS_VALUE = "1";
//
//    /** サービスクラス */
//    private final Hlgd03014S002Service service;
//
//    /**
//     * コンストラクタ
//     * 
//     * @param service サービス
//     */
//    @Autowired
//    public Hlgd03014S002Controller(Hlgd03014S002Service service) {
//        this.service = service;
//    }
//
//    /**
//     * 初期表示.
//     * 
//     * @param targetPersonId
//     * @param eventId
//     * @param model          Model
//     * @return 遷移先
//     */
//    @GetMapping(Constant.URI_HLGD03014S002)
//    public String init(@RequestParam(name = "targetPersonId", required = true) String targetPersonId,
//            @RequestParam(name = "eventId", required = true) String eventId, Model model) throws Exception {
//
//        // サービス呼び出す
//        Hlgd03014S002DisplayDto displayDto = service.init(targetPersonId, eventId);
//
//        /** 面談辞退理由 */
//        List<DeclineReasonType> declineReasonList = CodeEnum.getOrderedList(DeclineReasonType.class);
//        displayDto.setDeclineReasonList(declineReasonList);
//
//        // バンディングリザルトをモデルに詰める
//        model.addAttribute(Constant.DISPLAYDTO_HLGD03014S002, displayDto);
//        return Constant.VIEWID_HLGD03014S002;
//    }
//
//    /**
//     * 辞退
//     * 
//     * @param form
//     * @param bindingResult
//     * @param redirectAttributes
//     * @return 遷移先
//     * @throws Exception
//     */
//    @PostMapping(Constant.URI_HLGD03014S002 + Constant.MAPPING_HLGD03014S002_DECLINE)
//    public String decline(@Validated Hlgd03014S002Form form, BindingResult bindingResult,
//            RedirectAttributes redirectAttributes) throws Exception {
//
//        // セッション情報取得
//        Hlgd03014S002DisplayDto displayDto = (Hlgd03014S002DisplayDto) SessionUtil
//                .getSessionAttribute(Constant.DISPLAYDTO_HLGD03014S002);
//
//        // チェックエラーの場合
//        if (bindingResult.hasErrors()) {
//            // リフレッシュAttributesを作る
//            redirectAttributes.addFlashAttribute(Constant.FLASH_BINDING_RESULT_KEY, bindingResult);
//
//            return RedirectUtil.build(MappingConstant.URI_COUNSELING2, Constant.URI_HLGD03014S002)
//                    .param(TARGET_PERSON_ID, displayDto.getTargetPersonId()).param(EVENT_ID, displayDto.getEventId())
//                    .url();
//        }
//
//        // サービスパラメータ設定
//        Hlgd03014S002DeclineInterviewInfoDtoIn inDto = new Hlgd03014S002DeclineInterviewInfoDtoIn();
//        inDto.setTargetPersonId(displayDto.getTargetPersonId());
//        inDto.setEventId(displayDto.getEventId());
//        inDto.setDeclineReason(form.getDeclineReason());
//        inDto.setDeclineReasonDetails(form.getDeclineReasonDetails());
//
//        // サービスを呼び出す
//        service.decline(inDto);
//
//        // TOP画面へ遷移
//        return RedirectUtil.build(MappingConstant.URI_COUNSELING1, Constant.URI_HLGD00001S001).url();
//    }
//
//    /**
//     * TOPへ画面遷移
//     * 
//     * @param redirectAttributes
//     * @return 遷移先
//     * @throws BusinessException
//     */
//    @PostMapping(Constant.URI_HLGD03014S002 + Constant.MAPPING_HLGD03014S002_TRANSITION_TOP)
//    public String transitionToTop(RedirectAttributes redirectAttributes) throws Exception {
//
//        // TOP画面へ遷移
//        return RedirectUtil.build(MappingConstant.URI_COUNSELING1, Constant.URI_HLGD00001S001).url();
//    }
//
//    /**
//     * 面談予約へ画面遷移
//     * 
//     * @param redirectAttributes
//     * @return 遷移先
//     * @throws BusinessException
//     */
//    @PostMapping(Constant.URI_HLGD03014S002 + Constant.MAPPING_HLGD03014S002_TRANSITION_RESERVATION)
//    public String transitionToReservation(RedirectAttributes redirectAttributes) throws Exception {
//
//        // セッション情報取得
//        Hlgd03014S002DisplayDto displayDto = (Hlgd03014S002DisplayDto) SessionUtil
//                .getSessionAttribute(Constant.DISPLAYDTO_HLGD03014S002);
//
//        // 面談予約画面へ遷移
//        return RedirectUtil.build(MappingConstant.URI_COUNSELING2, Constant.URI_HLGD03014S001)
//                .param(TARGET_PERSON_ID, displayDto.getTargetPersonId()).param(EVENT_ID, displayDto.getEventId()).url();
//    }
//
//    /**
//     * 資格確認へ画面遷移
//     * 
//     * @param redirectAttributes
//     * @return 遷移先
//     * @throws BusinessException
//     */
//    @PostMapping(Constant.URI_HLGD03014S002 + Constant.MAPPING_HLGD03014S002_TRANSITION_QUALIFICATIONS)
//    public String transitionToQualifications(RedirectAttributes redirectAttributes) throws Exception {
//
//        // セッション情報取得
//        Hlgd03014S002DisplayDto displayDto = (Hlgd03014S002DisplayDto) SessionUtil
//                .getSessionAttribute(Constant.DISPLAYDTO_HLGD03014S002);
//
//        // TOP画面へ遷移
//        return RedirectUtil.build(MappingConstant.URI_COUNSELING1, Constant.URI_HLGD03004S023)
//                .param(TARGET_PERSON_ID, displayDto.getTargetPersonId()).param(EVENT_ID, displayDto.getEventId())
//                .param(STATUS, STATUS_VALUE).url();
//    }
//
//    /**
//     * 連絡先へ画面遷移
//     * 
//     * @param redirectAttributes
//     * @return 遷移先
//     * @throws BusinessException
//     */
//    @PostMapping(Constant.URI_HLGD03014S002 + Constant.MAPPING_HLGD03014S002_TRANSITION_CONTACT)
//    public String transitionToContact(RedirectAttributes redirectAttributes) throws Exception {
//
//        // セッション情報取得
//        Hlgd03014S002DisplayDto displayDto = (Hlgd03014S002DisplayDto) SessionUtil
//                .getSessionAttribute(Constant.DISPLAYDTO_HLGD03014S002);
//
//        // 連絡先画面へ遷移
//        return RedirectUtil.build(MappingConstant.URI_COUNSELING1, Constant.URI_HLGD03004S003)
//                .param(TARGET_PERSON_ID, displayDto.getTargetPersonId()).param(EVENT_ID, displayDto.getEventId())
//                .param(STATUS, STATUS_VALUE).url();
//    }
//
//    /**
//     * 事前問診へ画面遷移
//     * 
//     * @param redirectAttributes
//     * @return 遷移先
//     * @throws BusinessException
//     */
//    @PostMapping(Constant.URI_HLGD03014S002 + Constant.MAPPING_HLGD03014S002_TRANSITION_QUESTION)
//    public String transitionToQuestion(RedirectAttributes redirectAttributes) throws Exception {
//
//        // セッション情報取得
//        Hlgd03014S002DisplayDto displayDto = (Hlgd03014S002DisplayDto) SessionUtil
//                .getSessionAttribute(Constant.DISPLAYDTO_HLGD03014S002);
//
//        // 事前問診画面へ遷移
//        return RedirectUtil.build(MappingConstant.URI_COUNSELING1, Constant.URI_HLGD03004S018)
//                .param(TARGET_PERSON_ID, displayDto.getTargetPersonId()).param(EVENT_ID, displayDto.getEventId())
//                .param(STATUS, STATUS_VALUE).url();
//    }
//
//}
