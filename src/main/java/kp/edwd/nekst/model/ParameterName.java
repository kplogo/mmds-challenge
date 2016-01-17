package kp.edwd.nekst.model;

public enum ParameterName {
    QUERY_WORD_CNT,
    TFIDF_AVG,
    TFIDF_MIN,
    TFIDF_MAX,
    GROUP_DOM_AVG, //średnia waga słów w klastrze do którego należy domena strony
    GROUP_DOM_MIN, //min waga słów w klastrze do którego należy domena strony
    GROUP_DOM_MAX, //max waga słów w klastrze do którego należy domena strony
    CONTEXT_DESCRIPTION,
    CONTEXT_KEYWORDS,
    CONTEXT_TITLE,
    CONTEXT_HOST,
    CONTEXT_URL_PATH,
    HTML_TAG_H1,
    HTML_TAG_H2,
    HTML_TAG_H3,
    HTML_TAG_B,
    HTML_TAG_I, //liczba wszystkich unikalnych słów zdań w dokumencie
    HTML_TAG_A,
    WORD_PG_AVG, //avg pagerank dla słów w zapytaniu
    WORD_PG_MIN,//min pagerank dla słów w zapytaniu
    WORD_PG_MAX,//max pagerank dla słów w zapytaniu
    PHRASE,//czy zapytanie zawiera frazy ze słownika
    CONTEXT_DIFF_IP_REFLINK,
    PG_BIN_1000,//czy domena istnieje na liście pierwszego 1000 domen o najwyższym pagerank-u
    PG_BIN_10000,//czy domena istnieje na liście pierwszego 10000 domen o najwyższym pagerank-u (pomijając pierwszy 1000)
    PG_BIN_REST,
    PG,
    PG_TRUST,//trustrank domeny
    BADRANK,//badrank domeny
    HAS_DUPLICATES,
    PATH_LEN_ZERO,
    URL_WORD_CNT,
    URL_DEPTH,
    URL_PATH_LEN,
    HOST_DEPTH,
    HOST_LEN,
    HOST_SUFFIX_DEPTH,
    DOC_REAL_SEN_CNT, //liczba zdań w dokumencie
    DOC_WORD_CNT, //liczba wszystkich unikalnych słów zdań w dokumencie
    PROXIMITY_CONTENT,
    PROXIMITY_DESCRIPTION,
    PROXIMITY_KEYWORDS,
    PROXIMITY_TITLE,//bliskość słów z zapytania w tytule strony
    PROXIMITY_HOST,
    PROXIMITY_URL_PATH
}
