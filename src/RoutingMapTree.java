public class RoutingMapTree {

    public ExchangeList.Exchange root;
    ExchangeList eList = new ExchangeList();
    MobilePhoneSet phoneSet=new MobilePhoneSet();

    public RoutingMapTree(){
        root=new ExchangeList.Exchange(0);
        eList.head =root;
    }

    public Boolean containsNode(ExchangeList.Exchange a){
        ExchangeList.Exchange n =root;
        while (n != null)
        {
            if(n.uid==a.uid)
                return true;
            n=n.next;
        }
        return false;
    }


    public void switchOn(MobilePhone a, ExchangeList.Exchange b) throws Exception{
        if(this.containsNode(b)){
            a.switchOn();
            ExchangeList.Exchange n =root;
            while (n.uid != b.uid) {
                n=n.next;
            }
            a.exchange=n;
            phoneSet.Insert(a);
            n.residentSet().Insert(a);
            root.residentSet().Insert(a);
            while (n.parent!=null){
                ExchangeList.Exchange p =root;
                while (p.uid != n.parent.uid) {
                    p=p.next;
                }
                p.residentSet().Insert(a);
                n=n.parent();
            }
            return;
        }
        else
            throw new Exception("No exchange with identifier b");
    }

    public void switchOff(MobilePhone a) throws Exception{
        if((phoneSet.IsMember(a))==true)
            phoneSet.Delete(a);

        else
            throw new Exception("Mobile Phone out of coverage");
    }

    public String performAction(String actionMessage) throws Exception{
        String[] arr = actionMessage.split(" ");
        switch (arr[0]) {
            case "addExchange":{
                ExchangeList.Exchange a =new ExchangeList.Exchange(Integer.parseInt(arr[1]));
                ExchangeList.Exchange b =new ExchangeList.Exchange(Integer.parseInt(arr[2]));
                eList.Insert(a,b);
                return null;
            }
            case "switchOnMobile":{
                MobilePhone a = new MobilePhone(Integer.parseInt(arr[1]));
                ExchangeList.Exchange b =new ExchangeList.Exchange(Integer.parseInt(arr[2]));
                switchOn(a,b);
                return null;
            }
            case "switchOffMobile":{
                MobilePhone a = new MobilePhone(Integer.parseInt(arr[1]));
                switchOff(a);
                return null;
            }
            case "queryNthChild":{
                ExchangeList.Exchange a =new ExchangeList.Exchange(Integer.parseInt(arr[1]));
                return Integer.toString((a.child(Integer.parseInt(arr[2]))).uid);
            }
            case "queryMobilePhoneSet":{
                ExchangeList.Exchange l=root;
                while (l.uid != Integer.parseInt(arr[1])) {
                    l=l.next;
                }
                LinkedList.Node n=(l.residentPhoneSet.Intersection(phoneSet)).llist.head;
                String outSet = "";
                outSet=outSet + ((MobilePhone)n.data).number();
                n=n.next;
                while (n!=null){
                    outSet=outSet +", "+((MobilePhone)n.data).number();
                    n=n.next;
                }
                return outSet;
            }
            default:
                return "Enter a valid input";
        }
    }

//    public static void main(String[] args ) throws Exception{
//        RoutingMapTree C = new RoutingMapTree();
//        C.performAction("addExchange 0 1");
//        C.performAction("addExchange 0 2");
//        C.performAction("addExchange 0 3");
//        C.performAction("queryNthChild 0 0");
//        C.performAction("queryNthChild 0 2");
//        C.performAction("addExchange 1 4");
//        C.performAction("addExchange 1 5");
//        C.performAction("addExchange 2 6");
//        C.performAction("addExchange 2 7");
//        C.performAction("addExchange 2 8");
//        C.performAction("addExchange 3 9");
//        C.performAction("queryNthChild 2 0");
//        C.performAction("queryNthChild 3 0");
//        C.performAction("switchOnMobile 989 4");
//        C.performAction("switchOnMobile 876 4");
//        C.performAction("switchOnMobile 876 4");
//        C.performAction("queryMobilePhoneSet 4");
//        C.performAction("queryMobilePhoneSet 1");
//        C.performAction("switchOnMobile 656 5");
//        C.performAction("switchOnMobile 54 5");
//        C.performAction("queryMobilePhoneSet 1");
//        C.performAction("switchOffMobile 54");
//        C.performAction("queryMobilePhoneSet 1");
//        C.performAction("switchOnMobile 213 6");
//        C.performAction("switchOnMobile 568 7");
//        C.performAction("switchOnMobile 897 8");
//        C.performAction("switchOnMobile 295 8");
//        C.performAction("switchOnMobile 346 9");
//        C.performAction("queryMobilePhoneSet 0");
//        return;
//    }
}
