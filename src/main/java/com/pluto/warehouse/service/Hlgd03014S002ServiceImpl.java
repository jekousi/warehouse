//package jp.co.benefitone.hlgd.counseling2.service;
//
//import java.time.LocalDateTime;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import jp.co.benefitone.hlgd.common.type.InterviewMethodType;
//import jp.co.benefitone.hlgd.common.utils.CommonUtil;
//import jp.co.benefitone.hlgd.counseling2.constants.Constant;
//import jp.co.benefitone.hlgd.counseling2.dto.Hlgd03014S002DisplayDto;
//import jp.co.benefitone.hlgd.counseling2.repository.Hlgd03014S002DeclineInterviewInfoDtoIn;
//import jp.co.benefitone.hlgd.counseling2.repository.Hlgd03014S002InitDtoOut;
//import jp.co.benefitone.hlgd.counseling2.repository.Hlgd03014S002Repository;
//
///**
// * 
// * 面談予約状況画面サービスクラス
// * 
// * @author Benefit-One Corp.
// *
// */
//@Service
//public class Hlgd03014S002ServiceImpl implements Hlgd03014S002Service {
//    /** リポジトリクラス */
//    @Autowired
//    private Hlgd03014S002Repository repository;
//
//    /**
//     * 初期化
//     */
//    @Override
//    public Hlgd03014S002DisplayDto init(String targetPersonId, String eventId) throws Exception {
//
//        // 面談予約状況を取得
//        Hlgd03014S002InitDtoOut initOut = repository.init(targetPersonId, eventId);
//
//        // 情報がなければ、エラー
//        if (initOut == null) {
//            throw new Exception();
//        }
//
//        Hlgd03014S002DisplayDto displayDto = new Hlgd03014S002DisplayDto();
//
//        // 画面Dtoに詰める
//        displayDto.setTargetPersonId(targetPersonId);
//        displayDto.setEventId(eventId);
//        displayDto.setLatestUpdateDateTime(initOut.getUpdateDatetime());
//        displayDto.setQualificationsFlg(initOut.getIsEnteredQualified());
//        displayDto.setContactInformationFlg(initOut.getIsContact());
//
//        if (Constant.SUPPORT_METHOD_ICT.equals(initOut.getSupportMethod())) {
//            // ICT
//            displayDto.setPlaceName(Constant.SUPPORT_PLACE_TYPE_ICTM);
//        } else if (InterviewMethodType.VISIT_TYPE.getCode().equals(initOut.getInterviewMethod())) {
//            // 訪問
//            if (Constant.INTERVIEW_PLACE_WORK.equals(initOut.getInterviewPlace())) {
//                // 勤務先
//                displayDto.setPlaceName(initOut.getPlaceNameWork());
//            } else if (Constant.INTERVIEW_PLACE_HOME.equals(initOut.getInterviewPlace())) {
//                // 自宅
//                displayDto.setPlaceName(initOut.getPlaceNameHome());
//            } else {
//                // その他
//                displayDto.setPlaceName(initOut.getPlaceNameOther());
//            }
//        } else {
//            // 会場、事業所
//            // TODO 依頼情報．支援場所IDよりAPIから取得する
//            displayDto.setPlaceName("支援場所IDより取得する場所");
//        }
//
//        // TODO 事前問診が入力済かどうか未実装
//
//        return displayDto;
//    }
//
//    /**
//     * 面談辞退する
//     */
//    @Override
//    public void decline(Hlgd03014S002DeclineInterviewInfoDtoIn inDto) throws Exception {
//        LocalDateTime lockedUpdatetime = null;
//
//        // 面談情報をロック、排他
//        Hlgd03014S002DeclineInterviewInfoDtoIn lockedInterviewInfoDto = repository
//                .lockTInterviewInfo(inDto.getTargetPersonId(), inDto.getEventId());
//
//        if (lockedInterviewInfoDto == null) {
//            throw new Exception();
//        }
//
//        lockedUpdatetime = lockedInterviewInfoDto.getUpdateDatetimeInterview();
//
//        LocalDateTime oldUpdatetime = inDto.getUpdateDatetimeInterview();
//        // 排他
//        CommonUtil.doExclusive(lockedUpdatetime, oldUpdatetime);
//
//        // 面談情報を更新
//        repository.abolitionTInterviewInfo(inDto.getTargetPersonId(), inDto.getEventId());
//
//        // 面談記録をロック
//        repository.lockTInterviewRecord(inDto.getTargetPersonId(), inDto.getEventId());
//
//        // 面談記録を更新
//        repository.updateTInterviewRecord(inDto);
//
//        // 依頼情報をロック
//        repository.lockTTargetPersonalRequest(lockedInterviewInfoDto.getRequestId());
//
//        // 依頼情報を更新
//        repository.abolitionTTargetPersonalRequest(lockedInterviewInfoDto.getRequestId());
//
//    }
//
//}
