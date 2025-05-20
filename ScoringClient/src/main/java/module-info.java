module ScoringClient {
    requires spring.web;
    requires Common;
    provides dk.sdu.cbse.common.services.IScoreSender with dk.sdu.cbse.scoringsender.ScoreSenderImpl;

}