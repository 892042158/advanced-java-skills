package javax.script;

import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ScriptEngineManagerTest {
    public static ScriptEngine scriptEngine;
    @Test
    public void inject() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        Date date = new Date();
        System.out.println(date.getTime());
        engine.put("date", date);
        String js = "function getTime(){return date.getTime();}";
        engine.eval(js);
        Invocable invocable = (Invocable) engine;
        Long result = (Long) invocable.invokeFunction("getTime");
        System.out.println(result);
    }

    /**
     * 查看java 支持多少种js 加载器
     *
     * @Title getScriptEngineFactory
     * @author 于国帅
     * @date 2018年12月13日 下午3:21:24 void
     */
    @Test
    public void getScriptEngineFactory() {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = manager.getEngineFactories();
        for (ScriptEngineFactory factory : factories) {
            System.out.println(factory.getNames());
        }
    }


    @Test
    public  void test_gen2()  throws ScriptException, NoSuchMethodException{
        String jsStr = "function generateSignature(userId) {\n" +
                "    this.navigator = {\n" +
                "        userAgent: \"Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1\"\n" +
                "    }\n" +
                "    var e = {}\n" +
                "\n" +
                "    var r = (function () {\n" +
                "        function e(e, a, r) {\n" +
                "            return (b[e] || (b[e] = t(\"x,y\", \"return x \" + e + \" y\")))(r, a)\n" +
                "        }\n" +
                "\n" +
                "        function a(e, a, r) {\n" +
                "            return (k[r] || (k[r] = t(\"x,y\", \"return new x[y](\" + Array(r + 1).join(\",x[++y]\").substr(1) + \")\")))(e, a)\n" +
                "        }\n" +
                "\n" +
                "        function r(e, a, r) {\n" +
                "            var n, t, s = {}, b = s.d = r ? r.d + 1 : 0;\n" +
                "            for (s[\"$\" + b] = s, t = 0; t < b; t++) s[n = \"$\" + t] = r[n];\n" +
                "            for (t = 0, b = s.length = a.length; t < b; t++) s[t] = a[t];\n" +
                "            return c(e, 0, s)\n" +
                "        }\n" +
                "\n" +
                "        function c(t, b, k) {\n" +
                "            function u(e) {\n" +
                "                v[x++] = e\n" +
                "            }\n" +
                "\n" +
                "            function f() {\n" +
                "                return g = t.charCodeAt(b++) - 32, t.substring(b, b += g)\n" +
                "            }\n" +
                "\n" +
                "            function l() {\n" +
                "                try {\n" +
                "                    y = c(t, b, k)\n" +
                "                } catch (e) {\n" +
                "                    h = e, y = l\n" +
                "                }\n" +
                "            }\n" +
                "\n" +
                "            for (var h, y, d, g, v = [], x = 0; ;) switch (g = t.charCodeAt(b++) - 32) {\n" +
                "                case 1:\n" +
                "                    u(!v[--x]);\n" +
                "                    break;\n" +
                "                case 4:\n" +
                "                    v[x++] = f();\n" +
                "                    break;\n" +
                "                case 5:\n" +
                "                    u(function (e) {\n" +
                "                        var a = 0, r = e.length;\n" +
                "                        return function () {\n" +
                "                            var c = a < r;\n" +
                "                            return c && u(e[a++]), c\n" +
                "                        }\n" +
                "                    }(v[--x]));\n" +
                "                    break;\n" +
                "                case 6:\n" +
                "                    y = v[--x], u(v[--x](y));\n" +
                "                    break;\n" +
                "                case 8:\n" +
                "                    if (g = t.charCodeAt(b++) - 32, l(), b += g, g = t.charCodeAt(b++) - 32, y === c) b += g; else if (y !== l) return y;\n" +
                "                    break;\n" +
                "                case 9:\n" +
                "                    v[x++] = c;\n" +
                "                    break;\n" +
                "                case 10:\n" +
                "                    u(s(v[--x]));\n" +
                "                    break;\n" +
                "                case 11:\n" +
                "                    y = v[--x], u(v[--x] + y);\n" +
                "                    break;\n" +
                "                case 12:\n" +
                "                    for (y = f(), d = [], g = 0; g < y.length; g++) d[g] = y.charCodeAt(g) ^ g + y.length;\n" +
                "                    u(String.fromCharCode.apply(null, d));\n" +
                "                    break;\n" +
                "                case 13:\n" +
                "                    y = v[--x], h = delete v[--x][y];\n" +
                "                    break;\n" +
                "                case 14:\n" +
                "                    v[x++] = t.charCodeAt(b++) - 32;\n" +
                "                    break;\n" +
                "                case 59:\n" +
                "                    u((g = t.charCodeAt(b++) - 32) ? (y = x, v.slice(x -= g, y)) : []);\n" +
                "                    break;\n" +
                "                case 61:\n" +
                "                    u(v[--x][t.charCodeAt(b++) - 32]);\n" +
                "                    break;\n" +
                "                case 62:\n" +
                "                    g = v[--x], k[0] = 65599 * k[0] + k[1].charCodeAt(g) >>> 0;\n" +
                "                    break;\n" +
                "                case 65:\n" +
                "                    h = v[--x], y = v[--x], v[--x][y] = h;\n" +
                "                    break;\n" +
                "                case 66:\n" +
                "                    u(e(t[b++], v[--x], v[--x]));\n" +
                "                    break;\n" +
                "                case 67:\n" +
                "                    y = v[--x], d = v[--x], u((g = v[--x]).x === c ? r(g.y, y, k) : g.apply(d, y));\n" +
                "                    break;\n" +
                "                case 68:\n" +
                "                    u(e((g = t[b++]) < \"<\" ? (b--, f()) : g + g, v[--x], v[--x]));\n" +
                "                    break;\n" +
                "                case 70:\n" +
                "                    u(!1);\n" +
                "                    break;\n" +
                "                case 71:\n" +
                "                    v[x++] = n;\n" +
                "                    break;\n" +
                "                case 72:\n" +
                "                    v[x++] = +f();\n" +
                "                    break;\n" +
                "                case 73:\n" +
                "                    u(parseInt(f(), 36));\n" +
                "                    break;\n" +
                "                case 75:\n" +
                "                    if (v[--x]) {\n" +
                "                        b++;\n" +
                "                        break\n" +
                "                    }\n" +
                "                case 74:\n" +
                "                    g = t.charCodeAt(b++) - 32 << 16 >> 16, b += g;\n" +
                "                    break;\n" +
                "                case 76:\n" +
                "                    u(k[t.charCodeAt(b++) - 32]);\n" +
                "                    break;\n" +
                "                case 77:\n" +
                "                    y = v[--x], u(v[--x][y]);\n" +
                "                    break;\n" +
                "                case 78:\n" +
                "                    g = t.charCodeAt(b++) - 32, u(a(v, x -= g + 1, g));\n" +
                "                    break;\n" +
                "                case 79:\n" +
                "                    g = t.charCodeAt(b++) - 32, u(k[\"$\" + g]);\n" +
                "                    break;\n" +
                "                case 81:\n" +
                "                    h = v[--x], v[--x][f()] = h;\n" +
                "                    break;\n" +
                "                case 82:\n" +
                "                    u(v[--x][f()]);\n" +
                "                    break;\n" +
                "                case 83:\n" +
                "                    h = v[--x], k[t.charCodeAt(b++) - 32] = h;\n" +
                "                    break;\n" +
                "                case 84:\n" +
                "                    v[x++] = !0;\n" +
                "                    break;\n" +
                "                case 85:\n" +
                "                    v[x++] = void 0;\n" +
                "                    break;\n" +
                "                case 86:\n" +
                "                    u(v[x - 1]);\n" +
                "                    break;\n" +
                "                case 88:\n" +
                "                    h = v[--x], y = v[--x], v[x++] = h, v[x++] = y;\n" +
                "                    break;\n" +
                "                case 89:\n" +
                "                    u(function () {\n" +
                "                        function e() {\n" +
                "                            return r(e.y, arguments, k)\n" +
                "                        }\n" +
                "\n" +
                "                        return e.y = f(), e.x = c, e\n" +
                "                    }());\n" +
                "                    break;\n" +
                "                case 90:\n" +
                "                    v[x++] = null;\n" +
                "                    break;\n" +
                "                case 91:\n" +
                "                    v[x++] = h;\n" +
                "                    break;\n" +
                "                case 93:\n" +
                "                    h = v[--x];\n" +
                "                    break;\n" +
                "                case 0:\n" +
                "                    return v[--x];\n" +
                "                default:\n" +
                "                    u((g << 16 >> 16) - 16)\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        var n = this, t = n.Function, s = Object.keys || function (e) {\n" +
                "            var a = {}, r = 0;\n" +
                "            for (var c in e) a[r++] = c;\n" +
                "            return a.length = r, a\n" +
                "        }, b = {}, k = {};\n" +
                "        return r\n" +
                "    })()\n" +
                "    ('gr$Daten Иb/s!l y͒yĹg,(lfi~ah`{mv,-n|jqewVxp{rvmmx,&eff\u007Fkx[!cs\"l\".Pq%widthl\"@q&heightl\"vr*getContextx$\"2d[!cs#l#,*;?|u.|uc{uq$fontl#vr(fillTextx$$龘ฑภ경2<[#c}l#2q*shadowBlurl#1q-shadowOffsetXl#$$limeq+shadowColorl#vr#arcx88802[%c}l#vr&strokex[ c}l\"v,)}eOmyoZB]mx[ cs!0s$l$Pb<k7l l!r&lengthb%^l$1+s$j\u0002l  s#i$1ek1s$gr#tack4)zgr#tac$! +0o![#cj?o ]!l$b%s\"o ]!l\"l$b*b^0d#>>>s!0s%yA0s\"l\"l!r&lengthb<k+l\"^l\"1+s\"j\u0005l  s&l&z0l!$ +[\"cs\\'(0l#i\\'1ps9wxb&s() &{s)/s(gr&Stringr,fromCharCodes)0s*yWl ._b&s o!])l l Jb<k$.aj;l .Tb<k$.gj/l .^b<k&i\"-4j!\u001F+& s+yPo!]+s!l!l Hd>&l!l Bd>&+l!l <d>&+l!l 6d>&+l!l &+ s,y=o!o!]/q\"13o!l q\"10o!],l 2d>& s.{s-yMo!o!]0q\"13o!]*Ld<l 4d#>>>b|s!o!l q\"10o!],l!& s/yIo!o!].q\"13o!],o!]*Jd<l 6d#>>>b|&o!]+l &+ s0l-l!&l-l!i\\'1z141z4b/@d<l\"b|&+l-l(l!b^&+l-l&zl\\'g,)gk}ejo{\u007Fcm,)|yn~Lij~em[\"cl$b%@d<l&zl\\'l $ +[\"cl$b%b|&+l-l%8d<@b|l!b^&+ q$sign ', [e])\n" +
                "    return e.sign(userId)\n" +
                "}";
        scriptEngine = new ScriptEngineManager().getEngineByName("javascript");
        scriptEngine.eval(jsStr);

        String uid = "496625282450126";

        Invocable invocable = (Invocable)scriptEngine;
        Object result = invocable.invokeFunction("generateSignature",uid);

        System.out.println(result);
    }
}
