///**
// * 
// */
//package jp.co.benefitone.hlgd.counseling2.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDateTime;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import jp.co.benefitone.hlgd.common.type.InterviewMethodType;
//import jp.co.benefitone.hlgd.common.type.SupportMethodType;
//import jp.co.benefitone.hlgd.common.utils.DateUtils;
//import jp.co.benefitone.hlgd.counseling2.constants.Constant;
//import jp.co.benefitone.hlgd.counseling2.dto.Hlgd03014S002DisplayDto;
//import jp.co.benefitone.hlgd.counseling2.repository.Hlgd03014S002DeclineInterviewInfoDtoIn;
//import jp.co.benefitone.hlgd.counseling2.repository.Hlgd03014S002InitDtoOut;
//import jp.co.benefitone.hlgd.counseling2.repository.Hlgd03014S002Repository;
//
//@SpringBootTest(classes = Hlgd03014S002ServiceImpl.class)
//class Hlgd03014S002ServiceImplTest {
//
//    /** リポジトリクラス */
//    @MockBean
//    private Hlgd03014S002Repository repository;
//    /** 対象者一覧画面サービスクラス */
//    @Autowired
//    private Hlgd03014S002Service service;
//
//    @Nested
//    @DisplayName("初期化処理テスト")
//    class InitTest {
//        @DisplayName("初期化処理_面談予約状況なしエラー")
//        @Test
//        public void initNotInterviewTest() throws Exception {
//            String targetPersonId = "123";
//            String eventId = "001";
//
//            // @formatter:on
//            when(repository.init(any(), any())).thenReturn(null);
//
//            boolean err = false;
//            try {
//                service.init(targetPersonId, eventId);
//            } catch (Exception e) {
//                err = true;
//            }
//
//            assertTrue(err);
//
//        }
//
//        @DisplayName("初期化処理_ICT")
//        @Test
//        public void initIctTest() throws Exception {
//            String targetPersonId = "123";
//            String eventId = "001";
//            LocalDateTime now = DateUtils.getNow();
//
//            Hlgd03014S002InitDtoOut inDto = new Hlgd03014S002InitDtoOut();
//            inDto.setUpdateDatetime(now);
//            inDto.setSupportMethod(Constant.SUPPORT_METHOD_ICT);
//
//            // @formatter:on
//            when(repository.init(any(), any())).thenReturn(inDto);
//
//            Hlgd03014S002DisplayDto displayDto = service.init(targetPersonId, eventId);
//            assertNotNull(displayDto);
//            assertEquals(targetPersonId, displayDto.getTargetPersonId());
//            assertEquals(eventId, displayDto.getEventId());
//            assertEquals(now, displayDto.getLatestUpdateDateTime());
//            assertEquals(Constant.SUPPORT_PLACE_TYPE_ICTM, displayDto.getPlaceName());
//        }
//
//        @DisplayName("初期化処理_訪問_勤務先")
//        @Test
//        public void initVisitWorkTest() throws Exception {
//            String targetPersonId = "123";
//            String eventId = "001";
//            LocalDateTime now = DateUtils.getNow();
//
//            Hlgd03014S002InitDtoOut inDto = new Hlgd03014S002InitDtoOut();
//            inDto.setUpdateDatetime(now);
//            inDto.setInterviewMethod(SupportMethodType.IN_PERSON.getCode());
//            inDto.setInterviewPlace(Constant.INTERVIEW_PLACE_WORK);
//            String place = "会社";
//            inDto.setPlaceNameWork(place);
//
//            // @formatter:on
//            when(repository.init(any(), any())).thenReturn(inDto);
//
//            Hlgd03014S002DisplayDto displayDto = service.init(targetPersonId, eventId);
//            assertNotNull(displayDto);
//            assertEquals(targetPersonId, displayDto.getTargetPersonId());
//            assertEquals(eventId, displayDto.getEventId());
//            assertEquals(now, displayDto.getLatestUpdateDateTime());
//            assertEquals(place, displayDto.getPlaceName());
//        }
//
//        @DisplayName("初期化処理_訪問_自宅")
//        @Test
//        public void initVisitHomeTest() throws Exception {
//            String targetPersonId = "123";
//            String eventId = "001";
//            LocalDateTime now = DateUtils.getNow();
//
//            Hlgd03014S002InitDtoOut inDto = new Hlgd03014S002InitDtoOut();
//            inDto.setUpdateDatetime(now);
//            inDto.setInterviewMethod(InterviewMethodType.VISIT_TYPE.getCode());
//            inDto.setInterviewPlace(Constant.INTERVIEW_PLACE_HOME);
//            String place = "家";
//            inDto.setPlaceNameHome(place);
//
//            // @formatter:on
//            when(repository.init(any(), any())).thenReturn(inDto);
//
//            Hlgd03014S002DisplayDto displayDto = service.init(targetPersonId, eventId);
//            assertNotNull(displayDto);
//            assertEquals(targetPersonId, displayDto.getTargetPersonId());
//            assertEquals(eventId, displayDto.getEventId());
//            assertEquals(now, displayDto.getLatestUpdateDateTime());
//            assertEquals(place, displayDto.getPlaceName());
//        }
//
//        @DisplayName("初期化処理_訪問_その他")
//        @Test
//        public void initVisitOtherTest() throws Exception {
//            String targetPersonId = "123";
//            String eventId = "001";
//            LocalDateTime now = DateUtils.getNow();
//
//            Hlgd03014S002InitDtoOut inDto = new Hlgd03014S002InitDtoOut();
//            inDto.setUpdateDatetime(now);
//            inDto.setInterviewMethod(InterviewMethodType.VISIT_TYPE.getCode());
//            inDto.setInterviewPlace(Constant.INTERVIEW_PLACE_OTHER);
//            String place = "その他";
//            inDto.setPlaceNameOther(place);
//
//            // @formatter:on
//            when(repository.init(any(), any())).thenReturn(inDto);
//
//            Hlgd03014S002DisplayDto displayDto = service.init(targetPersonId, eventId);
//            assertNotNull(displayDto);
//            assertEquals(targetPersonId, displayDto.getTargetPersonId());
//            assertEquals(eventId, displayDto.getEventId());
//            assertEquals(now, displayDto.getLatestUpdateDateTime());
//            assertEquals(place, displayDto.getPlaceName());
//        }
//
//        @DisplayName("初期化処理_会場")
//        @Test
//        public void initVenueTest() throws Exception {
//            String targetPersonId = "123";
//            String eventId = "001";
//            LocalDateTime now = DateUtils.getNow();
//
//            Hlgd03014S002InitDtoOut inDto = new Hlgd03014S002InitDtoOut();
//            inDto.setUpdateDatetime(now);
//            inDto.setInterviewMethod(InterviewMethodType.VENUE_TYPE.getCode());
//
//            // @formatter:on
//            when(repository.init(any(), any())).thenReturn(inDto);
//
//            Hlgd03014S002DisplayDto displayDto = service.init(targetPersonId, eventId);
//            assertNotNull(displayDto);
//            assertEquals(targetPersonId, displayDto.getTargetPersonId());
//            assertEquals(eventId, displayDto.getEventId());
//            assertEquals(now, displayDto.getLatestUpdateDateTime());
//            // TODO 未実装
//            assertEquals("支援場所IDより取得する場所", displayDto.getPlaceName());
//        }
//
//    }
//
//    @Nested
//    @DisplayName("辞退処理テスト")
//    class DeclineTest {
//        @DisplayName("辞退処理_ロック対象なしエラー")
//        @Test
//        public void declineNotLockObjectTest() throws Exception {
//            // @formatter:on
//            when(repository.lockTInterviewInfo(any(), any())).thenReturn(null);
//
//            Hlgd03014S002DeclineInterviewInfoDtoIn inDto = new Hlgd03014S002DeclineInterviewInfoDtoIn();
//            boolean err = false;
//            try {
//                service.decline(inDto);
//            } catch (Exception e) {
//                err = true;
//            }
//
//            assertTrue(err);
//
//        }
//
//        @DisplayName("辞退処理_ロック対象あり")
//        @Test
//        public void declineNormalTest() throws Exception {
//            LocalDateTime now = DateUtils.getNow();
//
//            // @formatter:on
//            Hlgd03014S002DeclineInterviewInfoDtoIn lockedInterviewInfoDto = new Hlgd03014S002DeclineInterviewInfoDtoIn();
//            lockedInterviewInfoDto.setUpdateDatetimeInterview(now);
//            when(repository.lockTInterviewInfo(any(), any())).thenReturn(lockedInterviewInfoDto);
//
//            when(repository.abolitionTInterviewInfo(any(), any())).thenReturn(1);
//            when(repository.lockTInterviewRecord(any(), any())).thenReturn(lockedInterviewInfoDto);
//            when(repository.updateTInterviewRecord(any())).thenReturn(1);
//            when(repository.lockTTargetPersonalRequest(any())).thenReturn(lockedInterviewInfoDto);
//            when(repository.abolitionTTargetPersonalRequest(any())).thenReturn(1);
//
//            Hlgd03014S002DeclineInterviewInfoDtoIn inDto = new Hlgd03014S002DeclineInterviewInfoDtoIn();
//            inDto.setUpdateDatetimeInterview(now);
//            service.decline(inDto);
//
//        }
//    }
//}
