package imitori.utils;

public final class BEConstant {

    //NeoWordType
    public static final int NEO_JA_WORD = 1000;
    public static final int NEO_EN_WORD = 2000;
    public static final int NEO_VI_WORD = 3000;

    //Tuloai
    public static final int TL_ABBR = 10001; //Abbreviation
    public static final int TL_ADJ  = 10100; //Adjective
    public static final int TL_ADJ_I = 10101; //Adjective I
    public static final int TL_ADJ_NA = 10102; //Adjective NA
    public static final int TL_ADJ_NO = 10103; //Nouns which may take the gentitive case particle "no"
    public static final int TL_ADJ_PN = 10104; //Pre-noun adjectival (rentaishi)
    public static final int TL_ADJ_F = 10105; //Noun, verb, ect acting prenominally
    public static final int TL_ADJ_T = 10106; //"taru" adjective
    public static final int TL_ADV = 10200; //Adverb (fukushi)
    public static final int TL_ARCH = 10300; //Archaism
    public static final int TL_AUX = 10400; //Auxiliary
    public static final int TL_AUX_V = 10401; //Auxiliary verb
    public static final int TL_CONJ = 10500; //Conjunction; 
    public static final int TL_CTR = 10600; //Counter
    public static final int TL_COL = 10700; //Colloquialism
    public static final int TL_EXP = 10800; //Expression (phrase, clauses, etc)
    public static final int TL_FAM = 10900; //Familliar language
    public static final int TL_FEM = 11000; //Female term or language
    public static final int TL_GIKUN = 11100; //Giku (meaning) reading
    public static final int TL_HON = 11200; //Honorific or respecful (sonkeigo) language
    public static final int TL_HUM = 11300; //Humble (kenjougo) language
    public static final int TL_ID = 11400; //Idionmatic expression
    public static final int TL_INT = 11500; //Interjection (kandoushi)
    public static final int TL_IK = 11600; //Word containing irregular kanji usage
    public static final int TL_Ik = 11601; //Word containing irregular kana usage
    public static final int TL_IO = 11700; //Irregular okurigna usage
    public static final int TL_MA = 11800; //Martial art term
    public static final int TL_MALE = 11900; //Male term or language
    public static final int TL_M_SL = 12000; //Manga slang
    public static final int TL_N = 12100; //Noun (common) (tutsuumeishi)
    public static final int TL_N_ADV = 12101; //Adverbial noun (fukushiteikimeishi)
    public static final int TL_N_T = 12102; //Noun (temporal) (jisoumeishi)
    public static final int TL_OBS = 12200; //Obsolete term
    public static final int TL_OBSC = 12300; //Obscure term
    public static final int TL_oK = 12400; //Word containing out-dated kanji
    public static final int TL_ok = 12401; //out-dated or obsolete kana usage
    public static final int TL_POL = 12500; //Polie (teineigo) language
    public static final int TL_PRT = 12600; //Particle 
    public static final int TL_PREF = 12700; //Prefix
    public static final int TL_SL = 12800; //Slang
    public static final int TL_SENS = 12900; //Term with some sensitivity about its usage
    public static final int TL_SUF = 13000; // Suffix
    public static final int TL_uK = 13100; //Word usually written using kanji alone
    public static final int TL_uk = 13101; //Word usually written using kana alon
    public static final int TL_V1 = 13200; //Ichidan verb
    public static final int TL_V5 = 13300; //Godan verb (not completely classfied)
    public static final int TL_V5U = 13301; // Godan verb ending with "u"
    public static final int TL_V5K = 13302; // Godan verb ending with "ku"
    public static final int TL_V5T = 13303; // Godan verb ending with "tsu"
    public static final int TL_V5S = 13304; // Godan verb ending with "su"
    public static final int TL_V5N = 13305; // Godan verb edning with "nu"
    public static final int TL_V5M = 13306; // Godan verb ending with "mu"
    public static final int TL_V5R = 13307; // Godan verb ending with "ru"
    public static final int TL_V5K_S = 13310; // Godan verb ending with -iku/yuku special class
    public static final int TL_V5ARU = 13320; // Godan verb ending with aru special class
    public static final int TL_V5URU = 13330; //Godan verb ending with uru old class (old form of eru)
    public static final int TL_VI = 13400; // Intransitive verb
    public static final int TL_VS = 13500; // Noun or participle which takes the aux. verb suru
    public static final int TL_VS_S = 13600; //Suru verb special class
    public static final int TL_VK = 13700; //Kuru verb, special class
    public static final int TL_VT = 13800; //Transitive verb
    public static final int TL_VULG = 13900; //Vulgar expression of word
    public static final int TL_VZ = 14000; //Ichidan verb --zuru special class (alternative form of -jiru verbs)
    public static final int TL_P = 14100; //"Priority" entry
    public static final int TL_X = 14200; //Rude or X-rated term (not display in educational software)

    //Miscellaneous
    public static final int MISC_UK = 20000; // Word usually written using kana alone
    public static final int MISC_ABBR = 20100; // Abbreviation
    public static final int MISC_ARCH = 20200; //Archaism
    public static final int MISC_ATEJI = 20300; // Ateji reading of the kanji
    public static final int MISC_COL = 20400; //Colloqualism
    public static final int MISC_DEROG = 20500; //Derogatory term
    public static final int MISC_EK = 20600; //Exclusively kanji rarely just in kana
    public static final int MISC_FAM = 20700; //Faimiliar language
    public static final int MISC_FEM = 20800; //Female term or language
    public static final int MISC_GIKUN = 20900; //Gikun (meaning) reading
    public static final int MISC_HON = 21000; //Hnorific or respectful (sonkeigo) language
    public static final int MISC_HUM = 21100; //Humble (kenjogo) language
    public static final int MISC_ID = 21200; //Idiomatic expression
    public static final int MISC_IK = 21300; //Word containing irregular kanji usage
    public static final int MISC_Ik = 21301; //Word containing irregular kana usage
    public static final int MISC_IO = 21400; //Irregular okurigana usage
    public static final int MISC_MA = 21500; // Martial art term
    public static final int MISC_MALE = 21600; //Male term or language
    public static final int MISC_M_SL = 21700; //Manga slang
    public static final int MISC_OBS = 21800; //Obsolete term
    public static final int MISC_OBSC = 21900; //Obscure term
    public static final int MISC_OK = 22000; //Word containing out-dated kanji
    public static final int MISC_Ok = 23000; //Word containing outdate or obsolete kana
    public static final int MISC_POL = 24000; //Polite (teineigo) language
    public static final int MISC_SENS = 25000; //Term with some sensitivity about its usage
    public static final int MISC_SL = 26000; //Slang
    public static final int MISC_uK = 27000; //Word usually written using kanji alone
    public static final int MISC_VULG = 28000;//Vulga expresion of word

    //Language code
    public static final int LANG_KSB = 30000; //Kansai ben
    public static final int LANG_KTB = 30100; //Kanto ben
    public static final int LANG_KYB = 30200; //Kyoto ben
    public static final int LANG_KYU = 30300; //Kyushu ben
    public static final int LANG_OSB = 30400; //Osaka ben
    public static final int LANG_THB = 30500;
    public static final int LANG_TSB = 30600; //Tosa ben
    public static final int LANG_TSUG = 30700;

    //Naming dicionary 
    public static final int NAME_S = 40000; //Surname /family name
    public static final int NAME_M = 40100; //Male given name
    public static final int NAME_F = 40200; //Female given name
    public static final int NAME_U = 40300; //Unclassify name
    public static final int NAME_P = 40400; //Place name
    public static final int NAME_H = 40500; //Full name of a person

    // Search option
    public static final int SEARCH_EQUAL = 1;
    public static final int SEARCH_CONTAIN = 2;

    // Search result
    public static final int WORD_NOT_FOUND  = -1;
    public static final int WORD_FOUND_BUT_HAS_NULL_FIELD = -2;

    private BEConstant(){}
}