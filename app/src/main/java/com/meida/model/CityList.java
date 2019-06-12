package com.meida.model;

import java.util.List;

/**
 * 作者 亢兰兰
 * 时间 2018/8/30 0030
 * 公司  郑州软盟
 */

public class CityList {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"list":[{"id":440000,"name":"广东省","cityList":[{"id":440100,"name":"广州市","district":[{"id":440103,"name":"荔湾区"},{"id":440104,"name":"越秀区"},{"id":440105,"name":"海珠区"},{"id":440106,"name":"天河区"},{"id":440111,"name":"白云区"},{"id":440112,"name":"黄埔区"},{"id":440113,"name":"番禺区"},{"id":440114,"name":"花都区"},{"id":440115,"name":"南沙区"},{"id":440117,"name":"从化区"},{"id":440118,"name":"增城区"}]},{"id":440200,"name":"韶关市","district":[{"id":440203,"name":"武江区"},{"id":440204,"name":"浈江区"},{"id":440205,"name":"曲江区"},{"id":440222,"name":"始兴县"},{"id":440224,"name":"仁化县"},{"id":440229,"name":"翁源县"},{"id":440232,"name":"乳源瑶族自治县"},{"id":440233,"name":"新丰县"},{"id":440281,"name":"乐昌市"},{"id":440282,"name":"南雄市"}]},{"id":440300,"name":"深圳市","district":[{"id":440303,"name":"罗湖区"},{"id":440304,"name":"福田区"},{"id":440305,"name":"南山区"},{"id":440306,"name":"宝安区"},{"id":440307,"name":"龙岗区"},{"id":440308,"name":"盐田区"},{"id":440309,"name":"光明新区"},{"id":440310,"name":"坪山新区"},{"id":440311,"name":"大鹏新区"},{"id":440312,"name":"龙华新区"}]},{"id":440400,"name":"珠海市","district":[{"id":440402,"name":"香洲区"},{"id":440403,"name":"斗门区"},{"id":440404,"name":"金湾区"}]},{"id":440500,"name":"汕头市","district":[{"id":440507,"name":"龙湖区"},{"id":440511,"name":"金平区"},{"id":440512,"name":"濠江区"},{"id":440513,"name":"潮阳区"},{"id":440514,"name":"潮南区"},{"id":440515,"name":"澄海区"},{"id":440523,"name":"南澳县"}]},{"id":440600,"name":"佛山市","district":[{"id":440604,"name":"禅城区"},{"id":440605,"name":"南海区"},{"id":440606,"name":"顺德区"},{"id":440607,"name":"三水区"},{"id":440608,"name":"高明区"}]},{"id":440700,"name":"江门市","district":[{"id":440703,"name":"蓬江区"},{"id":440704,"name":"江海区"},{"id":440705,"name":"新会区"},{"id":440781,"name":"台山市"},{"id":440783,"name":"开平市"},{"id":440784,"name":"鹤山市"},{"id":440785,"name":"恩平市"}]},{"id":440800,"name":"湛江市","district":[{"id":440802,"name":"赤坎区"},{"id":440803,"name":"霞山区"},{"id":440804,"name":"坡头区"},{"id":440811,"name":"麻章区"},{"id":440823,"name":"遂溪县"},{"id":440825,"name":"徐闻县"},{"id":440881,"name":"廉江市"},{"id":440882,"name":"雷州市"},{"id":440883,"name":"吴川市"}]},{"id":440900,"name":"茂名市","district":[{"id":440902,"name":"茂南区"},{"id":440904,"name":"电白区"},{"id":440981,"name":"高州市"},{"id":440982,"name":"化州市"},{"id":440983,"name":"信宜市"}]},{"id":441200,"name":"肇庆市","district":[{"id":441202,"name":"端州区"},{"id":441203,"name":"鼎湖区"},{"id":441223,"name":"广宁县"},{"id":441224,"name":"怀集县"},{"id":441225,"name":"封开县"},{"id":441226,"name":"德庆县"},{"id":441283,"name":"高要市"},{"id":441284,"name":"四会市"}]},{"id":441300,"name":"惠州市","district":[{"id":441302,"name":"惠城区"},{"id":441303,"name":"惠阳区"},{"id":441322,"name":"博罗县"},{"id":441323,"name":"惠东县"},{"id":441324,"name":"龙门县"}]},{"id":441400,"name":"梅州市","district":[{"id":441402,"name":"梅江区"},{"id":441403,"name":"梅县区"},{"id":441422,"name":"大埔县"},{"id":441423,"name":"丰顺县"},{"id":441424,"name":"五华县"},{"id":441426,"name":"平远县"},{"id":441427,"name":"蕉岭县"},{"id":441481,"name":"兴宁市"}]},{"id":441500,"name":"汕尾市","district":[{"id":441502,"name":"城区"},{"id":441521,"name":"海丰县"},{"id":441523,"name":"陆河县"},{"id":441581,"name":"陆丰市"}]},{"id":441600,"name":"河源市","district":[{"id":441602,"name":"源城区"},{"id":441621,"name":"紫金县"},{"id":441622,"name":"龙川县"},{"id":441623,"name":"连平县"},{"id":441624,"name":"和平县"},{"id":441625,"name":"东源县"}]},{"id":441700,"name":"阳江市","district":[{"id":441702,"name":"江城区"},{"id":441704,"name":"阳东区"},{"id":441721,"name":"阳西县"},{"id":441781,"name":"阳春市"}]},{"id":441800,"name":"清远市","district":[{"id":441802,"name":"清城区"},{"id":441803,"name":"清新区"},{"id":441821,"name":"佛冈县"},{"id":441823,"name":"阳山县"},{"id":441825,"name":"连山壮族瑶族自治县"},{"id":441826,"name":"连南瑶族自治县"},{"id":441881,"name":"英德市"},{"id":441882,"name":"连州市"}]},{"id":441900,"name":"东莞市","district":[{"id":441901,"name":"莞城区"},{"id":441902,"name":"南城区"},{"id":441904,"name":"万江区"},{"id":441905,"name":"石碣镇"},{"id":441906,"name":"石龙镇"},{"id":441907,"name":"茶山镇"},{"id":441908,"name":"石排镇"},{"id":441909,"name":"企石镇"},{"id":441910,"name":"横沥镇"},{"id":441911,"name":"桥头镇"},{"id":441912,"name":"谢岗镇"},{"id":441913,"name":"东坑镇"},{"id":441914,"name":"常平镇"},{"id":441915,"name":"寮步镇"},{"id":441916,"name":"大朗镇"},{"id":441917,"name":"麻涌镇"},{"id":441918,"name":"中堂镇"},{"id":441919,"name":"高埗镇"},{"id":441920,"name":"樟木头镇"},{"id":441921,"name":"大岭山镇"},{"id":441922,"name":"望牛墩镇"},{"id":441923,"name":"黄江镇"},{"id":441924,"name":"洪梅镇"},{"id":441925,"name":"清溪镇"},{"id":441926,"name":"沙田镇"},{"id":441927,"name":"道滘镇"},{"id":441928,"name":"塘厦镇"},{"id":441929,"name":"虎门镇"},{"id":441930,"name":"厚街镇"},{"id":441931,"name":"凤岗镇"},{"id":441932,"name":"长安镇"}]},{"id":442000,"name":"中山市","district":[{"id":442001,"name":"石岐区"},{"id":442004,"name":"南区"},{"id":442005,"name":"五桂山区"},{"id":442006,"name":"火炬开发区"},{"id":442007,"name":"黄圃镇"},{"id":442008,"name":"南头镇"},{"id":442009,"name":"东凤镇"},{"id":442010,"name":"阜沙镇"},{"id":442011,"name":"小榄镇"},{"id":442012,"name":"东升镇"},{"id":442013,"name":"古镇镇"},{"id":442014,"name":"横栏镇"},{"id":442015,"name":"三角镇"},{"id":442016,"name":"民众镇"},{"id":442017,"name":"南朗镇"},{"id":442018,"name":"港口镇"},{"id":442019,"name":"大涌镇"},{"id":442020,"name":"沙溪镇"},{"id":442021,"name":"三乡镇"},{"id":442022,"name":"板芙镇"},{"id":442023,"name":"神湾镇"},{"id":442024,"name":"坦洲镇"}]},{"id":445100,"name":"潮州市","district":[{"id":445102,"name":"湘桥区"},{"id":445103,"name":"潮安区"},{"id":445122,"name":"饶平县"}]},{"id":445200,"name":"揭阳市","district":[{"id":445202,"name":"榕城区"},{"id":445203,"name":"揭东区"},{"id":445222,"name":"揭西县"},{"id":445224,"name":"惠来县"},{"id":445281,"name":"普宁市"}]},{"id":445300,"name":"云浮市","district":[{"id":445302,"name":"云城区"},{"id":445303,"name":"云安区"},{"id":445321,"name":"新兴县"},{"id":445322,"name":"郁南县"},{"id":445381,"name":"罗定市"}]}]}]}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 440000
         * name : 广东省
         * cityList : [{"id":440100,"name":"广州市","district":[{"id":440103,"name":"荔湾区"},{"id":440104,"name":"越秀区"},{"id":440105,"name":"海珠区"},{"id":440106,"name":"天河区"},{"id":440111,"name":"白云区"},{"id":440112,"name":"黄埔区"},{"id":440113,"name":"番禺区"},{"id":440114,"name":"花都区"},{"id":440115,"name":"南沙区"},{"id":440117,"name":"从化区"},{"id":440118,"name":"增城区"}]},{"id":440200,"name":"韶关市","district":[{"id":440203,"name":"武江区"},{"id":440204,"name":"浈江区"},{"id":440205,"name":"曲江区"},{"id":440222,"name":"始兴县"},{"id":440224,"name":"仁化县"},{"id":440229,"name":"翁源县"},{"id":440232,"name":"乳源瑶族自治县"},{"id":440233,"name":"新丰县"},{"id":440281,"name":"乐昌市"},{"id":440282,"name":"南雄市"}]},{"id":440300,"name":"深圳市","district":[{"id":440303,"name":"罗湖区"},{"id":440304,"name":"福田区"},{"id":440305,"name":"南山区"},{"id":440306,"name":"宝安区"},{"id":440307,"name":"龙岗区"},{"id":440308,"name":"盐田区"},{"id":440309,"name":"光明新区"},{"id":440310,"name":"坪山新区"},{"id":440311,"name":"大鹏新区"},{"id":440312,"name":"龙华新区"}]},{"id":440400,"name":"珠海市","district":[{"id":440402,"name":"香洲区"},{"id":440403,"name":"斗门区"},{"id":440404,"name":"金湾区"}]},{"id":440500,"name":"汕头市","district":[{"id":440507,"name":"龙湖区"},{"id":440511,"name":"金平区"},{"id":440512,"name":"濠江区"},{"id":440513,"name":"潮阳区"},{"id":440514,"name":"潮南区"},{"id":440515,"name":"澄海区"},{"id":440523,"name":"南澳县"}]},{"id":440600,"name":"佛山市","district":[{"id":440604,"name":"禅城区"},{"id":440605,"name":"南海区"},{"id":440606,"name":"顺德区"},{"id":440607,"name":"三水区"},{"id":440608,"name":"高明区"}]},{"id":440700,"name":"江门市","district":[{"id":440703,"name":"蓬江区"},{"id":440704,"name":"江海区"},{"id":440705,"name":"新会区"},{"id":440781,"name":"台山市"},{"id":440783,"name":"开平市"},{"id":440784,"name":"鹤山市"},{"id":440785,"name":"恩平市"}]},{"id":440800,"name":"湛江市","district":[{"id":440802,"name":"赤坎区"},{"id":440803,"name":"霞山区"},{"id":440804,"name":"坡头区"},{"id":440811,"name":"麻章区"},{"id":440823,"name":"遂溪县"},{"id":440825,"name":"徐闻县"},{"id":440881,"name":"廉江市"},{"id":440882,"name":"雷州市"},{"id":440883,"name":"吴川市"}]},{"id":440900,"name":"茂名市","district":[{"id":440902,"name":"茂南区"},{"id":440904,"name":"电白区"},{"id":440981,"name":"高州市"},{"id":440982,"name":"化州市"},{"id":440983,"name":"信宜市"}]},{"id":441200,"name":"肇庆市","district":[{"id":441202,"name":"端州区"},{"id":441203,"name":"鼎湖区"},{"id":441223,"name":"广宁县"},{"id":441224,"name":"怀集县"},{"id":441225,"name":"封开县"},{"id":441226,"name":"德庆县"},{"id":441283,"name":"高要市"},{"id":441284,"name":"四会市"}]},{"id":441300,"name":"惠州市","district":[{"id":441302,"name":"惠城区"},{"id":441303,"name":"惠阳区"},{"id":441322,"name":"博罗县"},{"id":441323,"name":"惠东县"},{"id":441324,"name":"龙门县"}]},{"id":441400,"name":"梅州市","district":[{"id":441402,"name":"梅江区"},{"id":441403,"name":"梅县区"},{"id":441422,"name":"大埔县"},{"id":441423,"name":"丰顺县"},{"id":441424,"name":"五华县"},{"id":441426,"name":"平远县"},{"id":441427,"name":"蕉岭县"},{"id":441481,"name":"兴宁市"}]},{"id":441500,"name":"汕尾市","district":[{"id":441502,"name":"城区"},{"id":441521,"name":"海丰县"},{"id":441523,"name":"陆河县"},{"id":441581,"name":"陆丰市"}]},{"id":441600,"name":"河源市","district":[{"id":441602,"name":"源城区"},{"id":441621,"name":"紫金县"},{"id":441622,"name":"龙川县"},{"id":441623,"name":"连平县"},{"id":441624,"name":"和平县"},{"id":441625,"name":"东源县"}]},{"id":441700,"name":"阳江市","district":[{"id":441702,"name":"江城区"},{"id":441704,"name":"阳东区"},{"id":441721,"name":"阳西县"},{"id":441781,"name":"阳春市"}]},{"id":441800,"name":"清远市","district":[{"id":441802,"name":"清城区"},{"id":441803,"name":"清新区"},{"id":441821,"name":"佛冈县"},{"id":441823,"name":"阳山县"},{"id":441825,"name":"连山壮族瑶族自治县"},{"id":441826,"name":"连南瑶族自治县"},{"id":441881,"name":"英德市"},{"id":441882,"name":"连州市"}]},{"id":441900,"name":"东莞市","district":[{"id":441901,"name":"莞城区"},{"id":441902,"name":"南城区"},{"id":441904,"name":"万江区"},{"id":441905,"name":"石碣镇"},{"id":441906,"name":"石龙镇"},{"id":441907,"name":"茶山镇"},{"id":441908,"name":"石排镇"},{"id":441909,"name":"企石镇"},{"id":441910,"name":"横沥镇"},{"id":441911,"name":"桥头镇"},{"id":441912,"name":"谢岗镇"},{"id":441913,"name":"东坑镇"},{"id":441914,"name":"常平镇"},{"id":441915,"name":"寮步镇"},{"id":441916,"name":"大朗镇"},{"id":441917,"name":"麻涌镇"},{"id":441918,"name":"中堂镇"},{"id":441919,"name":"高埗镇"},{"id":441920,"name":"樟木头镇"},{"id":441921,"name":"大岭山镇"},{"id":441922,"name":"望牛墩镇"},{"id":441923,"name":"黄江镇"},{"id":441924,"name":"洪梅镇"},{"id":441925,"name":"清溪镇"},{"id":441926,"name":"沙田镇"},{"id":441927,"name":"道滘镇"},{"id":441928,"name":"塘厦镇"},{"id":441929,"name":"虎门镇"},{"id":441930,"name":"厚街镇"},{"id":441931,"name":"凤岗镇"},{"id":441932,"name":"长安镇"}]},{"id":442000,"name":"中山市","district":[{"id":442001,"name":"石岐区"},{"id":442004,"name":"南区"},{"id":442005,"name":"五桂山区"},{"id":442006,"name":"火炬开发区"},{"id":442007,"name":"黄圃镇"},{"id":442008,"name":"南头镇"},{"id":442009,"name":"东凤镇"},{"id":442010,"name":"阜沙镇"},{"id":442011,"name":"小榄镇"},{"id":442012,"name":"东升镇"},{"id":442013,"name":"古镇镇"},{"id":442014,"name":"横栏镇"},{"id":442015,"name":"三角镇"},{"id":442016,"name":"民众镇"},{"id":442017,"name":"南朗镇"},{"id":442018,"name":"港口镇"},{"id":442019,"name":"大涌镇"},{"id":442020,"name":"沙溪镇"},{"id":442021,"name":"三乡镇"},{"id":442022,"name":"板芙镇"},{"id":442023,"name":"神湾镇"},{"id":442024,"name":"坦洲镇"}]},{"id":445100,"name":"潮州市","district":[{"id":445102,"name":"湘桥区"},{"id":445103,"name":"潮安区"},{"id":445122,"name":"饶平县"}]},{"id":445200,"name":"揭阳市","district":[{"id":445202,"name":"榕城区"},{"id":445203,"name":"揭东区"},{"id":445222,"name":"揭西县"},{"id":445224,"name":"惠来县"},{"id":445281,"name":"普宁市"}]},{"id":445300,"name":"云浮市","district":[{"id":445302,"name":"云城区"},{"id":445303,"name":"云安区"},{"id":445321,"name":"新兴县"},{"id":445322,"name":"郁南县"},{"id":445381,"name":"罗定市"}]}]
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String id;
            private String name;
            /**
             * id : 440100
             * name : 广州市
             * district : [{"id":440103,"name":"荔湾区"},{"id":440104,"name":"越秀区"},{"id":440105,"name":"海珠区"},{"id":440106,"name":"天河区"},{"id":440111,"name":"白云区"},{"id":440112,"name":"黄埔区"},{"id":440113,"name":"番禺区"},{"id":440114,"name":"花都区"},{"id":440115,"name":"南沙区"},{"id":440117,"name":"从化区"},{"id":440118,"name":"增城区"}]
             */

            private List<CityListBean> cityList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<CityListBean> getCityList() {
                return cityList;
            }

            public void setCityList(List<CityListBean> cityList) {
                this.cityList = cityList;
            }

            public static class CityListBean {
                private String id;
                private String name;
                /**
                 * id : 440103
                 * name : 荔湾区
                 */

                private List<DistrictBean> district;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<DistrictBean> getDistrict() {
                    return district;
                }

                public void setDistrict(List<DistrictBean> district) {
                    this.district = district;
                }

                public static class DistrictBean {
                    private String id;
                    private String name;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            }
        }
    }
}
