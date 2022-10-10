package collection;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.RamUsageEstimator;
import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: zhanmingwei
 */
@Slf4j
public class BitSetDemo {

    @Test
    public void test1() {
        BitSet bitSet = new BitSet(Integer.MAX_VALUE);
        bitSet.set(1234569845);
        log.info(String.valueOf(bitSet.get(1234569845)));
        bitSet.set(1234569845);
        log.info(String.valueOf(bitSet.get(1234569845)));
        log.info("bitSet:{}", RamUsageEstimator.humanSizeOf(bitSet));
    }

    @Test
    public void test() {
        HashMap<Long, BitSet> map = new HashMap<>(64);
        for (Long num : mobile) {
            long l = num/(10000000);
            int val = (int) (num - (10000000 * l));
            map.computeIfAbsent(l, k -> new BitSet()).set(val);
        }
//          使用该第三方工具比较简单直接，主要依靠JVM本身环境、参数及CPU架构计算头信息，再依据数据类型的标准计算实例字段大小，计算速度很快，另外使用较方便。
//        如果非要说这种方式有什么缺点的话，那就是这种方式计算所得的对象头大小是基于JVM声明规范的，并不是通过运行时内存地址计算而得，存在与实际大小不符的这种可能性。
        log.info("bitmap:{}", RamUsageEstimator.humanSizeOf(map.get(1301L)));
        log.info("bitmap:{}", RamUsageEstimator.humanSizeOf(map));

        log.info("Long[1000000]:{}",RamUsageEstimator.humanSizeOf(new long[1000000]) );
        log.info("Long[]:{}", RamUsageEstimator.humanSizeOf(mobile));
    }


   static long[] mobile =  new long[]{
        13001002310L,
        13001030000L,
        13001079116L,
        13001085809L,
        13001091328L,
        13001107280L,
        13001111222L,
        13001130064L,
        13001177849L,
        13001183739L,
        13001188062L,
        13001200199L,
        13001223210L,
        13001227157L,
        13001230101L,
        13001244167L,
        13001258852L,
        13001282717L,
        13001295768L,
        13001303097L,
        13001315168L,
        13001342969L,
        13001373603L,
        13001377810L,
        13001384068L,
        13001560198L,
        13001823158L,
        13001858359L,
        13001872111L,
        13001919313L,
        13001936840L,
        13001988838L,
        13001992509L,
        13002038015L,
        13002076221L,
        13002135002L,
        13002199803L,
        13002210365L,
        13002287189L,
        13002306450L,
        13002310097L,
        13002322727L,
        13002328693L,
        13002395537L,
        13002415135L,
        13002423775L,
        13002653610L,
        13002727661L,
        13002728997L,
        13002732525L,
        13002793900L,
        13002900986L,
        13002939204L,
        13002965216L,
        13003003052L,
        13003048098L,
        13003088521L,
        13003088753L,
        13003132900L,
        13003148154L,
        13003193629L,
        13003196421L,
        13003201522L,
        13003202188L,
        13003233999L,
        13003295886L,
        13003298169L,
        13003325066L,
        13003331837L,
        13003455892L,
        13003468898L,
        13003544903L,
        13003613489L,
        13003655800L,
        13003756573L,
        13003780087L,
        13004111333L,
        13004209911L,
        13004517326L,
        13004524410L,
        13004581099L,
        13004758860L,
        13005057968L,
        13005072666L,
        13005232311L,
        13005407388L,
        13005425422L,
        13005435966L,
        13005446495L,
        13005595907L,
        13005709796L,
        13005759163L,
        13005760180L,
        13005776165L,
        13005781911L,
        13005783163L,
        13005783973L,
        13005972845L,
        13005990653L,
        13005999386L,
        13006051210L,
        13006118819L,
        13006132229L,
        13006152026L,
        13006199996L,
        13006359882L,
        13006371892L,
        13006402890L,
        13006452345L,
        13006519437L,
        13006526912L,
        13006600029L,
        13006658525L,
        13007024861L,
        13007088708L,
        13007142481L,
        13007187777L,
        13007202693L,
        13007504768L,
        13007572502L,
        13007635872L,
        13007818070L,
        13008015553L,
        13008023263L,
        13008060913L,
        13008080949L,
        13008100261L,
        13008169670L,
        13008362545L,
        13008402515L,
        13008412070L,
        13008674621L,
        13008681038L,
        13008682079L,
        13008766161L,
        13008792196L,
        13008892268L,
        13008959211L,
        13009001027L,
        13009075559L,
        13009117544L,
        13009188028L,
        13009289870L,
        13009367131L,
        13009390711L,
        13009405978L,
        13009432618L,
        13009482229L,
        13009538283L,
        13009615553L,
        13009630580L,
        13009650924L,
        13009668866L,
        13009706497L,
        13009822555L,
        13009840558L,
        13009862327L,
        13009873230L,
        13009999574L,
        13011002511L,
        13011064728L,
        13011070120L,
        13011102133L,
        13011111739L,
        13011126441L,
        13011148914L,
        13011165589L,
        13011183199L,
        13011301941L,
        13011322530L,
        13011368669L,
        13011447029L,
        13011447968L,
        13011509881L,
        13011585801L,
        13011761286L,
        13011790023L,
        13011802671L,
        13011810801L,
        13011865678L,
        13011869231L,
        13011899522L,
        13011967299L,
        13011987518L,
        13012091126L,
        13012161059L,
        13012202221L,
        13012258803L,
        13012280351L,
        13012292809L,
        13012419825L,
        13012449735L,
        13012490111L,
        13012556846L,
        13012591973L,
        13012655523L,
        13012705256L,
        13012756246L,
        13012779768L,
        13012810699L,
        13012950030L,
        13012951138L,
        13012997328L,
        13013025001L,
        13013067690L,
        13013171629L,
        13013249993L,
        13013382013L,
        13013664609L,
        13013799019L,
        13013935465L,
        13013965610L,
        13013993797L,
        13014200703L,
        13014209476L,
        13014267176L,
        13014277776L,
        13014382600L,
        13014447771L,
        13014500110L,
        13014669189L,
        13014766619L,
        13014851920L,
        13014866669L,
        13015002822L,
        13015120427L,
        13015218056L,
        13015263588L,
        13015350474L,
        13015479186L,
        13015489636L,
        13015507901L,
        13015520999L,
        13015523397L,
        13015588661L,
        13015667667L,
        13015973657L,
        13015979920L,
        13016183017L,
        13016257677L,
        13016756779L,
        13016766082L,
        13016784000L,
        13016824097L,
        13016964958L,
        13016982382L,
        13017000759L,
        13017043621L,
        13017191937L,
        13017317675L,
        13017332345L,
        13017391722L,
        13017421925L,
        13017435110L,
        13017477876L,
        13017527753L,
        13017622363L,
        13017625598L,
        13017635141L,
        13017674201L,
        13017693052L,
        13017811278L,
        13018023171L,
        13018040000L,
        13018196626L,
        13018228555L,
        13018261286L,
        13018267048L,
        13018293615L,
        13018379085L,
        13018575818L,
        13018809123L,
        13018962471L,
        13018967880L,
        13019045166L,
        13019054217L,
        13019336075L,
        13019361981L,
        13019379333L,
        13019419567L,
        13019497337L,
        13019621786L,
        13019690578L,
        13019766737L,
        13019902122L,
        13019961931L,
        13020000973L,
        13020001723L,
        13020056437L,
        13020092666L,
        13020130591L,
        13020401256L,
        13020528775L,
        13020555753L,
        13020650952L,
        13020719762L,
        13020757009L,
        13021016605L,
        13021123848L,
        13021177787L,
        13021259813L,
        13021310083L,
        13021322688L,
        13021338670L,
        13021357625L,
        13021381878L,
        13021395857L,
        13021412138L,
        13021499679L,
        13021680373L,
        13021693579L,
        13021729084L,
        13021800390L,
        13021866225L,
        13021989495L,
        13022083340L,
        13022106872L,
        13022146226L,
        13022199887L,
        13022222060L,
        13022225687L,
        13022246121L,
        13022258852L,
        13022293199L,
        13022326985L,
        13022327179L,
        13022360881L,
        13022552614L,
        13022788521L,
        13022807060L,
        13022835118L,
        13022855972L,
        13022878152L,
        13022888065L,
        13022904999L,
        13022938742L,
        13022953310L,
        13022969509L,
        13022980007L,
        13022980419L,
        13022993767L,
        13023105567L,
        13023125222L,
        13023157816L,
        13023260148L,
        13023317355L,
        13023355996L,
        13023420282L,
        13023481021L,
        13023830318L,
        13023860825L,
        13023882977L,
        13023883970L,
        13024019333L,
        13024101204L,
        13024145656L,
        13024157178L,
        13024195657L,
        13024500509L,
        13024572036L,
        13025120697L,
        13025125223L,
        13025156135L,
        13025282327L,
        13025284121L,
        13025358590L,
        13025364446L,
        13025381317L,
        13025442478L,
        13025730978L,
        13025833917L,
        13025875279L,
        13026059668L,
        13026126113L,
        13026128325L,
        13026165030L,
        13026171316L,
        13026314500L,
        13026322669L,
        13026337559L,
        13026533041L,
        13026556234L,
        13026563151L,
        13026791536L,
        13026997253L,
        13026998435L,
        13027016948L,
        13027138772L,
        13027209609L,
        13027411569L,
        13027421149L,
        13027466079L,
        13027503928L,
        13027533279L,
        13027553119L,
        13027638982L,
        13027672936L,
        13027755777L,
        13027761855L,
        13027771059L,
        13028024725L,
        13028035666L,
        13028058260L,
        13028131839L,
        13028138116L,
        13028147577L,
        13028247590L,
        13028398886L,
        13028410448L,
        13028421654L,
        13028696797L,
        13028774473L,
        13028819162L,
        13028906778L,
        13029007274L,
        13029062500L,
        13029372668L,
        13029374991L,
        13029408761L,
        13029465218L,
        13029605308L,
        13029623017L,
        13029720333L,
        13029766866L,
        13029852903L,
        13029860258L,
        13029941444L,
        13030039977L,
        13030041696L,
        13030054889L,
        13030062111L,
        13030098503L,
        13030119113L,
        13030126869L,
        13030196911L,
        13030228663L,
        13030272992L,
        13030282532L,
        13030519880L,
        13030878887L,
        13031001157L,
        13031070560L,
        13031078836L,
        13031096668L,
        13031113281L,
        13031115643L,
        13031122333L,
        13031122889L,
        13031138882L,
        13031430756L,
        13031433331L,
        13031661248L,
        13031669751L,
        13031725217L,
        13031744089L,
        13031782132L,
        13031823770L,
        13031836586L,
        13031882269L,
        13031888799L,
        13032009660L,
        13032038467L,
        13032200002L,
        13032231477L,
        13032242519L,
        13032269482L,
        13032338393L,
        13032389300L,
        13032465597L,
        13032535001L,
        13032536171L,
        13032664596L,
        13032675516L,
        13032700911L,
        13032726251L,
        13032730102L,
        13032790999L,
        13032810057L,
        13032882370L,
        13032887008L,
        13032952395L,
        13032970308L,
        13033249412L,
        13033273087L,
        13033300603L,
        13033433337L,
        13033469146L,
        13033520216L,
        13033633007L,
        13033677573L,
        13033728352L,
        13033990681L,
        13034006913L,
        13034121857L,
        13034125916L,
        13034323748L,
        13034342055L,
        13034559970L,
        13034578749L,
        13034588977L,
        13034913696L,
        13034952188L,
        13034972988L,
        13035063919L,
        13035081109L,
        13035212152L,
        13035226373L,
        13035326841L,
        13035416778L,
        13035597298L,
        13035689222L,
        13035903743L,
        13035965047L,
        13036024222L,
        13036102851L,
        13036201067L,
        13036515888L,
        13036536879L,
        13036591191L,
        13036596873L,
        13036700626L,
        13036705185L,
        13036779827L,
        13036780346L,
        13036813777L,
        13037117201L,
        13037209863L,
        13037401522L,
        13037426254L,
        13037559876L,
        13037725106L,
        13037846260L,
        13037865009L,
        13037919484L,
        13037986898L,
        13038001822L,
        13038208548L,
        13038609288L,
        13038630086L,
        13038730731L,
        13038733000L,
        13038772673L,
        13038985733L,
        13038987259L,
        13039005203L,
        13039125275L,
        13039170797L,
        13039190999L,
        13039218174L,
        13039466586L,
        13039652338L,
        13039662622L,
        13039667570L,
        13039990658L,
        13040055783L,
        13040311889L,
        13040628906L,
        13040657729L,
        13040854529L,
        13040984616L,
        13041026263L,
        13041091788L,
        13041143555L,
        13041196788L,
        13041206671L,
        13041315551L,
        13041390325L,
        13041426169L,
        13041619060L,
        13042016645L,
        13042045373L,
        13042295660L,
        13042298801L,
        13042484445L,
        13042581652L,
        13042598379L,
        13042699444L,
        13043210986L,
        13043251212L,
        13043537538L,
        13043887643L,
        13044274903L,
        13044304727L,
        13044333857L,
        13045022505L,
        13045064811L,
        13045129753L,
        13045130531L,
        13045259315L,
        13045296676L,
        13045405832L,
        13045413977L,
        13045437255L,
        13045657409L,
        13045668181L,
        13045768978L,
        13045875555L,
        13045875818L,
        13046128950L,
        13046212277L,
        13046221996L,
        13046273301L,
        13046456347L,
        13046502540L,
        13046658323L,
        13047219370L,
        13047413721L,
        13047460525L,
        13047687347L,
        13047935535L,
        13048055493L,
        13048483498L,
        13048583583L,
        13048701139L,
        13048755999L,
        13048825750L,
        13048938049L,
        13048984077L,
        13049039125L,
        13049053537L,
        13049238966L,
        13049387707L,
        13049398624L,
        13049468722L,
        13049615419L,
        13049627867L,
        13049800240L,
        13049884822L,
        13049992555L,
        13050282826L,
        13050343352L,
        13050395569L,
        13050432662L,
        13050446273L,
        13050551253L,
        13050561813L,
        13050646751L,
        13051001158L,
        13051017043L,
        13051035710L,
        13051071070L,
        13051071228L,
        13051103319L,
        13051107797L,
        13051107820L,
        13051120315L,
        13051128050L,
        13051178809L,
        13051195767L,
        13051209069L,
        13051233008L,
        13051257085L,
        13051291464L,
        13051327311L,
        13051334626L,
        13051338880L,
        13051378342L,
        13051400910L,
        13051466222L,
        13051520678L,
        13051582345L,
        13051618960L,
        13051619901L,
        13051631320L,
        13051695062L,
        13051738885L,
        13051837517L,
        13051854499L,
        13051902392L,
        13051985209L,
        13052008951L,
        13052052010L,
        13052070336L,
        13052232286L,
        13052293988L,
        13052326985L,
        13052388588L,
        13052422983L,
        13052460053L,
        13052554999L,
        13052558450L,
        13052602991L,
        13052737290L,
        13052740627L,
        13052743671L,
        13052808506L,
        13052831650L,
        13053219765L,
        13053408286L,
        13053520289L,
        13053888783L,
        13053921970L,
        13054052327L,
        13054165877L,
        13054220681L,
        13054260011L,
        13054333390L,
        13054404216L,
        13054564706L,
        13054693008L,
        13055067710L,
        13055082189L,
        13055115560L,
        13055401329L,
        13055422099L,
        13055436767L,
        13055511115L,
        13055511117L,
        13055527571L,
        13055732510L,
        13055930223L,
        13056016002L,
        13056120017L,
        13056209371L,
        13056256603L,
        13056435564L,
        13056480666L,
        13056510236L,
        13056612203L,
        13056678172L,
        13056686813L,
        13056883195L,
        13056918879L,
        13056978228L,
        13057171176L,
        13057185525L,
        13057211278L,
        13057232822L,
        13057307701L,
        13057400748L,
        13057503605L,
        13057521680L,
        13057567710L,
        13057628872L,
        13057689761L,
        13057691732L,
        13057696379L,
        13057731995L,
        13057770968L,
        13057969286L,
        13058092676L,
        13058120850L,
        13058132450L,
        13058133288L,
        13058226596L,
        13058275887L,
        13058404181L,
        13058410888L,
        13058846575L,
        13058866609L,
        13058891300L,
        13058998942L,
        13059090123L,
        13059116111L,
        13059181703L,
        13059221958L,
        13059410544L,
        13059462038L,
        13059573344L,
        13059598908L,
        13059804567L,
        13059960365L,
        13059969808L,
        13060021312L,
        13060093628L,
        13060188783L,
        13060240752L,
        13060428325L,
        13060475099L,
        13060603714L,
        13060618004L,
        13060802549L,
        13060843968L,
        13060865519L,
        13060917090L,
        13060921250L,
        13060936925L,
        13060951721L,
        13060988438L,
        13061173785L,
        13061200007L,
        13061223376L,
        13061240603L,
        13061252083L,
        13061261271L,
        13061300006L,
        13061312566L,
        13061354029L,
        13061359395L,
        13061359901L,
        13061381758L,
        13061416318L,
        13061458087L,
        13061491677L,
        13061602405L,
        13061616658L,
        13061655678L,
        13061667966L,
        13061668646L,
        13061736153L,
        13061738872L,
        13061813076L,
        13061816502L,
        13061887200L,
        13062005051L,
        13062252635L,
        13062341468L,
        13062605728L,
        13062633438L,
        13062643778L,
        13062672597L,
        13062690031L,
        13062777352L,
        13062798297L,
        13062828980L,
        13062905506L,
        13063005966L,
        13063339950L,
        13063502829L,
        13063563333L,
        13063598036L,
        13063782062L,
        13064033030L,
        13064072939L,
        13064189279L,
        13064765069L,
        13065073619L,
        13065204317L,
        13065309836L,
        13065385551L,
        13065417387L,
        13065445188L,
        13065496903L,
        13065601121L,
        13065745830L,
        13065796940L,
        13065800616L,
        13065806969L,
        13065861919L,
        13066009333L,
        13066097629L,
        13066118435L,
        13066189555L,
        13066248926L,
        13066333391L,
        13066343447L,
        13066362787L,
        13066641410L,
        13066646015L,
        13066660553L,
        13066663200L,
        13066668453L,
        13066762132L,
        13066766248L,
        13066787501L,
        13066954745L,
        13066999598L,
        13067026093L,
        13067033322L,
        13067110881L,
        13067208310L,
        13067234430L,
        13067239650L,
        13067316776L,
        13067466630L,
        13067561756L,
        13067812370L,
        13067980293L,
        13068003330L,
        13068042241L,
        13068089869L,
        13068095647L,
        13068176881L,
        13068312333L,
        13068474686L,
        13068518223L,
        13068622245L,
        13068686447L,
        13068702566L,
        13068707193L,
        13068849548L,
        13068887878L,
        13069009990L,
        13069301118L,
        13069311272L,
        13069332169L,
        13069362171L,
        13069535902L,
        13069656800L,
        13069704321L,
        13069755789L,
        13069776694L,
        13069862083L,
        13069866630L,
        13069872569L,
        13069966789L,
        13070003244L,
        13070023696L,
        13070061022L,
        13070083275L,
        13070160729L,
        13070173631L,
        13070187960L,
        13070214552L,
        13070226864L,
        13070282761L,
        13070342370L,
        13070455384L,
        13070504678L,
        13070528580L,
        13070651533L,
        13070710118L,
        13070744113L,
        13070811113L,
        13071018126L,
        13071022208L,
        13071022838L,
        13071055730L,
        13071091709L,
        13071100658L,
        13071111833L,
        13071123000L,
        13071175707L,
        13071181760L,
        13071194430L,
        13071218836L,
        13071244706L,
        13071246362L,
        13071260092L,
        13071296978L,
        13071432680L,
        13071456867L,
        13071592571L,
        13071853974L,
        13071922965L,
        13071953005L,
        13071975107L,
        13072055855L,
        13072082888L,
        13072166146L,
        13072195998L,
        13072243353L,
        13072254419L,
        13072267556L,
        13072277575L,
        13072286099L,
        13072301341L,
        13072331750L,
        13072407008L,
        13072454112L,
        13072464666L,
        13072531225L,
        13072773489L,
        13072821010L,
        13072837280L,
        13072871009L,
        13072883441L,
        13072925988L,
        13073180513L,
        13073310255L,
        13073354335L,
        13073470831L,
        13073504986L,
        13073531374L,
        13073586392L,
        13073591980L,
        13073596830L,
        13073602060L,
        13073640815L,
        13073709961L,
        13073737272L,
        13073888764L,
        13074102034L,
        13074110532L,
        13074160716L,
        13074195366L,
        13074236145L,
        13074237905L,
        13074251915L,
        13074513178L,
        13074745974L,
        13074773735L,
        13074777105L,
        13075016105L,
        13075189987L,
        13075205717L,
        13075208283L,
        13075282764L,
        13075311215L,
        13075347609L,
        13075347838L,
        13075372502L,
        13075381518L,
        13075400128L
    };
}
