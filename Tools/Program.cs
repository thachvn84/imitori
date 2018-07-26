using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace PhantichAnhViet
{
    class TlSubMean
    {
        public string mean;
        public List<Dictionary<string, string>> phrase;

        public TlSubMean()
        {
            phrase = new List<Dictionary<string, string>>();
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
        public List<SideWord> sideword;

        public TlMean()
        {
            means = new List<TlSubMean>();
            sideword = new List<SideWord>();
        }
    }
    class ChuyenNganh
    {
        public string name;
        public List<string> mean;

        public ChuyenNganh()
        {
            mean = new List<string>();
        }

    }
    class WRecord
    {
        public string word;
        public string spell;
        public List<TlMean> means;
        public List<ChuyenNganh> fieldMeans;

        public WRecord()
        {
            means = new List<TlMean>();
            fieldMeans = new List<ChuyenNganh>();
        }
    }
    class Program
    {
        static string inputstring = "boot\t@boot /bu:t/\n*  danh từ\n- to boot thêm vào đó, nữa\n*  danh từ\n- giày ống\n- ngăn để hành lý (đằng sau xe ô tô, xe ngựa)\n- (sử học) giày tra tấn (dụng cụ tra tấn hình giày ống)\n!to beat somebody out his boots\n- đánh ai nhừ tử; đánh ai thâm tím mình mẩy\n!to die in ones's boots\n- (xem) die\n!to get the boot\n- (từ lóng) bị đuổi, bị tống cổ ra, bị đá đít\n!to give somebody the boot\n- (từ lóng) đá đít ai, đuổi ai, tống cổ ai\n!to have one's heart in one's boots\n- (xem) heart\n!to lick someone's boots\n- liếm gót ai, bợ đỡ ai\n!like old boots\n- (từ lóng) rán hết sức, ; dữ dội, mạnh mẽ, mãnh liệt\n!over shoes over boots\n- (tục ngữ) đã trót thì phải trét\n!the boot is on the other leg\n- sự thật lại là ngược lại\n- trách nhiệm thuộc về phía bên kia\n*  ngoại động từ\n- (từ Mỹ,nghĩa Mỹ) đi giày ống cho\n- đá (ai)\n- (sử học) tra tấn (bằng giày tra tấn)\n!to boot out\n- đuổi ra, tống cổ ra, đá đít ((nghĩa bóng))\n@Chuyên ngành kinh tế\n-đuổi việc\n-ngăn để hành lý (xe hơi)\n-sự sa thải\n-tra chương trình\n@Chuyên ngành kỹ thuật\n-bao bì\n-cái chắn bánh xe\n-đế cọc\n-khởi động\n-khởi động hệ thống\n-mũ cọc\n-phễu\n-tấm ép\n-vỏ bọc\n-vỏ chắn bụi\n-vỏ chụp\n@Lĩnh vực: ô tô\n-cái kẹp bánh xe\n-tấm chẹn\n@Lĩnh vực: xây dựng\n-hố thu (nước)\n-mũ bịt\n@Lĩnh vực: điện lạnh\n-vỏ bảo vệ (cáp, dây dẫn)";

        static void submain(string[] args)
        {
            WRecord wr = new WRecord();
            wr.word = inputstring.Split('\t')[0];
            string[] inarr = inputstring.Split('@');
            Console.Out.WriteLine(wr.word);
            Console.Out.WriteLine(wr.spell);
            TlMean wm = new TlMean();
            TlSubMean swm = new TlSubMean();
            SideWord sidewm = new SideWord();
            int tlcount = 0;
            int subtlcount = 0;
            int sidewordcnt = 0;
            for (int i = 1; i < inarr.Length; i++)
            {
                Console.Out.WriteLine(inarr[i]);
                bool general = false;
                bool hasSpell = false;
                string[] line = inarr[i].Split('\n');
                if (line[0].IndexOf('/') > 0)
                {
                    hasSpell = true;
                    string field = line[0].Split('/')[0].Trim();
                    Console.WriteLine("[" + field + "][" + wr.word + "]");
                    if (field == wr.word)
                    {
                        general = true;
                    }
                    else
                    {
                        general = false;
                    }
                }
                else
                {
                    string field = line[0].Trim();
                    if (field == wr.word)
                    {
                        general = true;
                    }
                    else
                    {
                        general = false;
                    }
                }
                if (general)
                {

                    if (hasSpell)
                    {
                        wr.spell = line[0].Substring(
                                                line[0].IndexOf('/') + 1,
                                                line[0].LastIndexOf('/') - line[0].IndexOf('/') - 1)
                                           .Trim();
                    }

                    for (int j = 1; j < line.Length; j++)
                    {
                        if (line[j].Length > 1)
                        {
                            switch (line[j].ToArray()[0])
                            {
                                case '*':
                                    {
                                        if (tlcount != 0)
                                        {
                                            if (sidewordcnt > 0)
                                            {
                                                sidewm.means.Add(swm);
                                                wm.sideword.Add(sidewm);
                                                sidewordcnt = 0;
                                            }
                                            wr.means.Add(wm);
                                        }
                                        wm = new TlMean();
                                        tlcount++;
                                        string tl = line[j].Substring(1, line[j].Length - 1).Trim();
                                        wm.tl = tl;
                                        Console.WriteLine("Tu loai: " + tl);
                                    }
                                    break;
                                case '-':
                                    {
                                        if (subtlcount != 0)
                                        {
                                            if (sidewordcnt != 0)
                                            {
                                                sidewm.means.Add(swm);
                                            }
                                            else
                                            {
                                                wm.means.Add(swm);
                                            }
                                        }
                                        swm = new TlSubMean();
                                        subtlcount++;
                                        string m = line[j].Substring(1, line[j].Length - 1).Trim();
                                        swm.mean = m;
                                    }
                                    break;
                                case ('!'):
                                    {
                                        if (subtlcount > 0)
                                        {
                                            wm.means.Add(swm);
                                            subtlcount = 0;
                                        }
                                        if (subtlcount > 0)
                                        {
                                            if (sidewordcnt != 0)
                                            {
                                                sidewm.means.Add(swm);
                                                wm.sideword.Add(sidewm);
                                                subtlcount = 0;
                                            }
                                            else
                                            {
                                                wm.means.Add(swm);
                                                subtlcount = 0;
                                            }
                                        }

                                        sidewm = new SideWord();
                                        sidewm.word = line[j].Substring(1, line[j].Length - 1).Trim();
                                        sidewordcnt++;
                                    }
                                    break;
                                case '=':
                                    {
                                        string prs = line[j].Substring(1, line[j].Length - 1)
                                                            .Substring(0, line[j].IndexOf('+') - 1)
                                                            .Trim();
                                        string prsm = line[j].Substring(
                                                        line[j].IndexOf('+') + 1,
                                                        line[j].Length - 1 - line[j].IndexOf('+')
                                                      ).Trim();
                                        Console.Out.WriteLine("prs: " + prs + "; prsm: " + prsm);
                                        Dictionary<string, string> phrs = new Dictionary<string, string>();
                                        phrs.Add(prs, prsm);
                                        swm.phrase.Add(phrs);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
                else
                {
                    if (tlcount > 0)
                    {
                        if (subtlcount > 0)
                        {
                            wm.means.Add(swm);
                            subtlcount = 0;
                        }
                        if (sidewordcnt > 0)
                        {
                            wm.sideword.Add(sidewm);
                            subtlcount = 0;
                        }
                        wr.means.Add(wm);
                        tlcount = 0;
                    }

                    ChuyenNganh cn = new ChuyenNganh();
                    cn.name = line[0].Trim();
                    for (int j = 1; j < line.Length; j++)
                    {
                        if (line[j].Length > 1)
                        {
                            switch (line[j].ToArray()[0])
                            {
                                case '-':
                                    {
                                        string m = line[j].Substring(1, line[j].Length - 1).Trim();
                                        cn.mean.Add(m);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    wr.fieldMeans.Add(cn);
                }


                Console.Out.WriteLine("------------");
            }
            using (System.IO.StreamWriter jsonwrite = new System.IO.StreamWriter("WRSave.txt"))
            {
                string output = JsonConvert.SerializeObject(wr, Formatting.Indented);
                jsonwrite.Write(output);
                jsonwrite.Close();
            }
            Console.ReadKey();
        }

		static ChuyenNganh ProcessChuyenNganh(string input) {
			ChuyenNganh result = new ChuyenNganh();
			string[] allLine = input.Split('\n');
			result.name = allLine[0].Trim();
			for (int i = 1; i < allLine.Length; i++) {
				string curLine = allLine[i];
				if (curLine.Length > 0)
				{
					result.mean.Add(curLine.Substring(1, curLine.Length - 1).Trim());
				}
			}
			return result;
		}

		static string getWord(string input) {
			string result = "";
			//   boot /bu:t/
			result = input.Split('/')[0].Trim();
			return result;
		}

		static string getSpell(string input) {
			string result = "";
			//   boot /bu:t/
			if (input.Split('/').Length > 2) {
				result = input.Substring(input.IndexOf('/'), input.LastIndexOf('/') - input.IndexOf('/'));
			}
			return result;
		}

		static Dictionary<string, string> getSubPhrase(string input) {
			Dictionary<string, string> result = new Dictionary<string, string>();
			string key = input.Split('+')[0].Trim();
			string value = input.Split('+')[1].Trim();
			result.Add(key, value);
			return result;
		}
		static TlSubMean getSubMean(string input) {
			TlSubMean result = new TlSubMean();
			string[] subMean_allLine = input.Split('\n');
			result.mean = subMean_allLine[0];

			string[] subPhrase = input.Split('=');
			for (int i = 1; i < subPhrase.Length; i++) {
				result.phrase.Add(getSubPhrase(subPhrase[i]));
			}

			return result;
		}

		static SideWord processSideWord(string input) {
			SideWord sideWord = new SideWord();
			string[] sw_allLine = input.Split('\n');
			sideWord.word = sw_allLine[0].Trim();

			string[] sideWord_subMean = input.Split('-');
			for (int i = 1; i < sideWord_subMean.Length; i++) {
				TlSubMean sbmean = getSubMean(sideWord_subMean[i]);
				sideWord.means.Add(sbmean);
			}
			return sideWord;
		}

		static TlMean getTuloai(string input) {
			Console.Out.WriteLine("----getTuLoai: " + input + "-----");
			TlMean result = new TlMean();
			string[] allLine = input.Split('\n');


			string[] side_word_block = input.Split('!');
			string tlMainBlock = side_word_block[0];

			string[] tlMainblock_allLine = tlMainBlock.Split('\n');
			result.tl = tlMainblock_allLine[0].Trim();

			string[] subMeanBlock = tlMainBlock.Split('-');
			for (int i = 1; i < subMeanBlock.Length; i++) {
				TlSubMean subMean = getSubMean(subMeanBlock[i]);
				result.means.Add(subMean);
			}

			if (side_word_block.Length > 1) {
				for (int i = 1; i < side_word_block.Length; i++) {
					SideWord side_word = new SideWord();
					side_word = processSideWord(side_word_block[i]);
					result.sideword.Add(side_word);
				}
			}
			return result;
		}
		static WRecord ProcessMainWord(string input) {
			WRecord result = new WRecord();
			string[] allLine = input.Split('\n');
			string word_spell = allLine[0];
			result.word = getWord(word_spell);
			result.spell = getSpell(word_spell);

			string[] TuLoaiArray = input.Split('*');
			for (int i = 1; i < TuLoaiArray.Length; i++) {
				TlMean tuloai = getTuloai(TuLoaiArray[i]);
				result.means.Add(tuloai);
			}

			return result;
		}

		static void Main(string[] args) {
			string word = inputstring.Split('\t')[0];
			string content = inputstring.Split('\t')[1];

			string[] content_line = content.Split('\n');
			WRecord mainWord = new WRecord();

			for (int i = 0; i < content_line.Length; i++) {
				Console.Out.WriteLine(content_line[i]);	
  			}

			string[] TypeBlock = inputstring.Split('@');
			if (TypeBlock.Length < 2) {
				return;
			}
			string TypeMainBlock = TypeBlock[1];

			mainWord = ProcessMainWord(TypeMainBlock);
            
            //Processing sub block
			for (int i = 2; i < TypeBlock.Length; i++) {
				ChuyenNganh chuyenNganh = new ChuyenNganh();
				chuyenNganh = ProcessChuyenNganh(TypeBlock[i]);

			}
		}
    }
}
