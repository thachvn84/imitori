using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace PhantichAnhViet
{
    class Phrase
    {
        public string phrase;
        public string mean;
    }
    class TlSubMean
    {
        public string mean;
        public List<Phrase> phrases;

        public TlSubMean()
        {
            phrases = new List<Phrase>();
        }
    }
    class SideWord
    {
        public string word;
        public List<TlSubMean> means;

        public SideWord()
        {
            means = new List<TlSubMean>();
        }
    }
    class TlMean
    {
        public string tl;
        public List<TlSubMean> means;
        public List<SideWord> sidewords;

        public TlMean()
        {
            means = new List<TlSubMean>();
            sidewords = new List<SideWord>();
        }
    }
    class ChuyenNganh
    {
        public string name;
        public List<string> means;

        public ChuyenNganh()
        {
            means = new List<string>();
        }

    }
    class WRecord
    {
        public string word;
        public string spell;
        public List<TlMean> means;
        public List<ChuyenNganh> fieldmeans;

        public WRecord()
        {
            means = new List<TlMean>();
            fieldmeans = new List<ChuyenNganh>();
        }
    }
    class Program
    {
        //static string inputstring = "love	@love /lʌv/\\n*  danh từ\\n- lòng yêu, tình thương\\n=love of one's country+ lòng yêu nước\\n=a mother's love for her children+ tình mẹ yêu con\\n- tình yêu, mối tình, ái tình\\n=first love+ mối tình đầu\\n=never trifle with love+ không nên đùa bỡn với tình yêu\\n=to be in love with+ yêu (ai)\\n=to fall in love with+ đâm ra yêu (phải lòng) (ai)\\n=to make love to someone+ tán tỉnh ai, tỏ tình với ai; ôm ấp hôn hít ai, ăn nằm với ai\\n=to marry for love+ kết hôn vì tình\\n- người yêu, người tình\\n- thần ái tình\\n- (thông tục) người đáng yêu; vật đáng yêu\\n- (thể dục,thể thao) điểm không, không (quần vợt)\\n=love all+ không không (hai bên cùng không được điểm nào)\\n=love forty+ không bốn mươi\\n=a love set+ một ván thua trắng (người thua không được điểm nào)\\n!to love in a cottage\\n- ái tình và nước lã\\n!one can't get it for love or money\\n- không có cách gì lấy được cái đó\\n!to play for love\\n- chơi vì thích không phải vì tiền\\n!there is no love lost between them\\n- chúng nó ghét nhau như đào đất đổ đi\\n*  ngoại động từ\\n- yêu, thương, yêu mến\\n=to love one another+ yêu nhau, thương nhau\\n- thích, ưa thích\\n=to love sports+ thích thể thao\\n=to love music+ thích âm nhạc\\n=he loves to be praised+ nó thích được khen";

        static ChuyenNganh ProcessChuyenNganh(string input)
        {
            ChuyenNganh result = new ChuyenNganh();
            string[] allLine = input.Split(new string[] { "\\n" }, StringSplitOptions.None);
            result.name = allLine[0].Trim();
            for (int i = 1; i < allLine.Length; i++)
            {
                string curLine = allLine[i];
                if (curLine.Length > 0)
                {
                    result.means.Add(curLine.Substring(1, curLine.Length - 1).Trim());
                }
            }
            return result;
        }

        static string getWord(string input)
        {
            string result = "";
            //   boot /bu:t/
            result = input.Split('/')[0].Trim();
            return result;
        }

        static string getSpell(string input)
        {
            string result = "";
            //   boot /bu:t/
            if (input.Split('/').Length > 2)
            {
                result = input.Substring(input.IndexOf('/')+1, input.LastIndexOf('/') - input.IndexOf('/') - 1);
            }
            return result;
        }

        static Phrase getSubPhrase(string input)
        {
            Phrase result = new Phrase();
            result.phrase = input.Split('+')[0].Trim();
            result.mean = input.Split('+')[1].Trim();
            
            return result;
        }
        static TlSubMean getSubMean(string input)
        {
            TlSubMean result = new TlSubMean();
            string[] subMean_allLine = input.Split(new string[] { "\\n" }, StringSplitOptions.None);
            result.mean = subMean_allLine[0];

            string[] subPhrase = input.Split(new string[] { "\\n=" }, StringSplitOptions.None);
            for (int i = 1; i < subPhrase.Length; i++)
            {
                result.phrases.Add(getSubPhrase(subPhrase[i]));
            }

            return result;
        }

        static SideWord processSideWord(string input)
        {
            SideWord sideWord = new SideWord();
            string[] sw_allLine = input.Split(new string[] { "\\n" }, StringSplitOptions.None);
            sideWord.word = sw_allLine[0].Trim();

            string[] sideWord_subMean = input.Split(new string[] { "\\n-" }, StringSplitOptions.None);
            for (int i = 1; i < sideWord_subMean.Length; i++)
            {
                TlSubMean sbmean = getSubMean(sideWord_subMean[i]);
                sideWord.means.Add(sbmean);
            }
            return sideWord;
        }

        static TlMean getTuloai(string input)
        {
            //Console.Out.WriteLine("----getTuLoai: " + input + "-----");
            TlMean result = new TlMean();
            string[] allLine = input.Split(new string[] { "\\n" }, StringSplitOptions.None);


            string[] side_word_block = input.Split(new string[] { "\\n!" }, StringSplitOptions.None);
            string tlMainBlock = side_word_block[0];

            string[] tlMainblock_allLine = tlMainBlock.Split(new string[] { "\\n" }, StringSplitOptions.None);
            result.tl = tlMainblock_allLine[0].Trim();

            string[] subMeanBlock = tlMainBlock.Split(new string[] { "\\n-" }, StringSplitOptions.None);
            for (int i = 1; i < subMeanBlock.Length; i++)
            {
                TlSubMean subMean = getSubMean(subMeanBlock[i]);
                result.means.Add(subMean);
            }

            if (side_word_block.Length > 1)
            {
                for (int i = 1; i < side_word_block.Length; i++)
                {
                    SideWord side_word = new SideWord();
                    side_word = processSideWord(side_word_block[i]);
                    result.sidewords.Add(side_word);
                }
            }
            return result;
        }
        static WRecord ProcessMainWord(string input)
        {
            WRecord result = new WRecord();
            string[] allLine = input.Split(new string[] { "\\n" }, StringSplitOptions.None);
            string word_spell = allLine[0];
            int start_content = 0;
            result.spell = getSpell(word_spell);
            if (result.spell.Length > 0)
            {
                result.word = getWord(word_spell);
                start_content = 1;
            }

            string[] TuLoaiArray = input.Split(new string[] { "\\n*" }, StringSplitOptions.None);
            for (int i = start_content; i < TuLoaiArray.Length; i++)
            {
                TlMean tuloai = getTuloai(TuLoaiArray[i]);
                result.means.Add(tuloai);
            }

            return result;
        }

        static WRecord parseOneWord(string inputstring)
        {
            string word = inputstring.Split('\t')[0];
            string content = inputstring.Split('\t')[1];

            string[] content_line = content.Split(new string[] { "\\n" }, StringSplitOptions.None);
            WRecord mainWord = new WRecord();
            /*
            for (int i = 0; i < content_line.Length; i++)
            {
                Console.Out.WriteLine(content_line[i]);
            }
            */
            string[] TypeBlock = inputstring.Split(new string[] { "\\n@"}, StringSplitOptions.None);
            if (TypeBlock.Length < 2)
            {
                return null;
            }
            string TypeMainBlock = TypeBlock[1];

            mainWord = ProcessMainWord(TypeMainBlock);
            if (mainWord.word == null || mainWord.word.Length == 0)
            {
                mainWord.word = new string(TypeBlock[0].Trim().ToCharArray());
            }

            //Processing sub block
            for (int i = 2; i < TypeBlock.Length; i++)
            {
                ChuyenNganh chuyenNganh = new ChuyenNganh();
                chuyenNganh = ProcessChuyenNganh(TypeBlock[i]);
                mainWord.fieldmeans.Add(chuyenNganh);
            }
            return mainWord;
        }

        static void Main(string[] args)
        {

            List<WRecord> EVdict = new List<WRecord>();
            using (System.IO.StreamWriter jsonwrite = new System.IO.StreamWriter("WRSave.txt"))
            {
                System.IO.StreamReader hvfile = new System.IO.StreamReader("en_vi.txt");
                int count = 0;
                while (!hvfile.EndOfStream)
                {
                    Console.Out.WriteLine(count++);
                    string inline = hvfile.ReadLine();
                    WRecord mainWord = parseOneWord(inline);
                    EVdict.Add(mainWord);
                }
                string output = JsonConvert.SerializeObject(EVdict, Formatting.Indented);
                jsonwrite.Write(output);
                jsonwrite.Close();
            }
            Console.ReadKey();

        }
    }
}