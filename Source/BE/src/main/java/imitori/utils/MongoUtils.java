package imitori.utils;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import imitori.entity.mongodb.OppositeRelMonEntity;
import imitori.entity.mongodb.RelatedRelMonEntity;
import imitori.entity.mongodb.SimilarRelMonEntity;
import imitori.entity.mongodb.TransRelMonEntity;

public final class MongoUtils {
    /*
     *  modifyCondition: Modify the Query condition to search.
     */
    public static Query modifyCondition(String key, String value, int option) {
        Query query = new Query();
        switch(option) {
            case BEConstant.SEARCH_EQUAL:{
                query.addCriteria(Criteria.where(key).is(value));
            } break;
            case BEConstant.SEARCH_CONTAIN: {
                query.addCriteria(Criteria.where(key).in(value));
            } break;
            default: {

            } break;
        }
        return query;
    }

    public static ArrayList<SimilarRelMonEntity> mergeSimRel(ArrayList<SimilarRelMonEntity> src, ArrayList<SimilarRelMonEntity> des) {
        ArrayList<SimilarRelMonEntity> res = new ArrayList<>();
        if (des != null) {
            if (src != null) {
                for (int i = 0; i < des.size(); i++) {
                    boolean found_sim = false;
                    for (int j = 0; j < src.size(); j++) {
                        if (src.get(j).to == des.get(i).to) {
                            found_sim = true;
                            src.get(j).score = des.get(j).score;
                            break;
                        }
                    }
                    if (!found_sim) {
                        src.add(des.get(i));
                    }
                }
            } else {
                src = new ArrayList<>();
                src = des;
            }
        }
        res = src;
        return res;
    }

    public static ArrayList<RelatedRelMonEntity> mergeRelRel(ArrayList<RelatedRelMonEntity> src, ArrayList<RelatedRelMonEntity> des) {
        ArrayList<RelatedRelMonEntity> res = new ArrayList<>();
        if (des != null) {
            if (src != null) {
                for (int i = 0; i < des.size(); i++) {
                    boolean found_sim = false;
                    for (int j = 0; j < src.size(); j++) {
                        if (src.get(j).to == des.get(i).to) {
                            found_sim = true;
                            src.get(j).score = des.get(j).score;
                            break;
                        }
                    }
                    if (!found_sim) {
                        src.add(des.get(i));
                    }
                }
            } else {
                src = new ArrayList<>();
                src = des;
            }
        }
        res = src;
        return res;
    }

    public static ArrayList<OppositeRelMonEntity> mergeOppRel(ArrayList<OppositeRelMonEntity> src, ArrayList<OppositeRelMonEntity> des) {
        ArrayList<OppositeRelMonEntity> res = new ArrayList<>();
        if (des != null) {
            if (src != null) {
                for (int i = 0; i < des.size(); i++) {
                    boolean found_sim = false;
                    for (int j = 0; j < src.size(); j++) {
                        if (src.get(j).to == des.get(i).to) {
                            found_sim = true;
                            src.get(j).score = des.get(j).score;
                            break;
                        }
                    }
                    if (!found_sim) {
                        src.add(des.get(i));
                    }
                }
            } else {
                src = new ArrayList<>();
                src = des;
            }
        }
        res = src;
        return res;
    }

    public static ArrayList<TransRelMonEntity> mergeTransRel(ArrayList<TransRelMonEntity> src, ArrayList<TransRelMonEntity> des) {
        ArrayList<TransRelMonEntity> res = new ArrayList<>();
        if (des != null) {
            if (src != null) {
                for (int i = 0; i < des.size(); i++) {
                    boolean found_sim = false;
                    for (int j = 0; j < src.size(); j++) {
                        if (src.get(j).to == des.get(i).to) {
                            found_sim = true;
                            src.get(j).score = des.get(j).score;
                            break;
                        }
                    }
                    if (!found_sim) {
                        src.add(des.get(i));
                    }
                }
            } else {
                src = new ArrayList<>();
                src = des;
            }
        }
        res = src;
        return res;
    }

}