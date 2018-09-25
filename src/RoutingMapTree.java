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


    public void switchOn(MobilePhone a, ExchangeList.Exchange b){
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
    }

    public void switchOff(MobilePhone a){
        if((phoneSet.IsMember(a))==true){
            LinkedList.Node temp = phoneSet.llist.head;
            while (temp != null && (((MobilePhone)temp.data).number())!=a.number()){
                temp=temp.next;
            }
            ExchangeList.Exchange n =  ((MobilePhone)temp.data).location();
            phoneSet.Delete(a);
            while (n!=null){
                n.residentPhoneSet.Delete(a);
                n = n.parent();
            }

        }
    }

    public ExchangeList.Exchange findPhone(MobilePhone m){
        LinkedList.Node temp = phoneSet.llist.head;
        if((phoneSet.IsMember(m))==true) {
            while (temp != null && (((MobilePhone) temp.data).number()) != m.number()) {
                temp = temp.next;
            }
        }
        ExchangeList.Exchange n = ((MobilePhone) temp.data).location();
        return n;

    }

    public ExchangeList.Exchange lowestRouter (ExchangeList.Exchange a, ExchangeList.Exchange b){
        ExchangeList.Exchange n =root;
        while (n != null)
        {
            if(n.uid==a.uid)
                break;
            n=n.next;
        }
        ExchangeList.Exchange temp = n.parent();
        if(a.uid==b.uid)
            return a;
        else{
            while (temp!=null){
                if(temp.isChild(b)==true)
                    return temp;
                temp =temp.parent();
            }

        }
        return root;
    }

    public LinkedList routeCall(MobilePhone a, MobilePhone b){
        ExchangeList.Exchange one = findPhone(a);
        ExchangeList.Exchange two = findPhone(b);
        int n = lowestRouter(one,two).uid;
        LinkedList alpha = new LinkedList();
        LinkedList beta = new LinkedList();
        alpha.head = new LinkedList.Node(one.uid);
        beta.head = new LinkedList.Node(two.uid);
        LinkedList.Node temp1 = alpha.head;
        LinkedList.Node temp2 = beta.head;

        while (two.uid!=n) {
            temp2.next = new LinkedList.Node(two.parent.uid);
            (temp2.next).prev = temp2;
            temp2 = temp2.next;
            two = two.parent;
        }

        while (one.uid!=n) {
            temp1.next = new LinkedList.Node(one.parent.uid);
            temp1 = temp1.next;
            one = one.parent;
        }
        temp2 = temp2.prev;
        while (temp2!=null)
        {
            temp1.next = new LinkedList.Node(temp2.data);
            temp1 = temp1.next;
            temp2 =temp2.prev;
        }




        return alpha;
    }

    public void movePhone(MobilePhone a, ExchangeList.Exchange b){
        if(phoneSet.IsMember(a)){
            switchOff(a);
            switchOn(a,b);
        }

    }

    public String performAction(String actionMessage){
        String[] arr = actionMessage.split(" ");
        switch (arr[0]) {
            case "addExchange":{
                ExchangeList.Exchange a =root;
                while (a != null)
                {
                    if(a.uid==Integer.parseInt(arr[1]))
                        break;
                    a=a.next;
                }
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
                System.out.println(actionMessage + ": "+Integer.toString((a.child(Integer.parseInt(arr[2]))).uid));
                return null;
            }
            case "queryMobilePhoneSet":{
                ExchangeList.Exchange l=root;
                while (l.uid != Integer.parseInt(arr[1])) {
                    l=l.next;
                }
                LinkedList.Node n=l.residentPhoneSet.llist.head;
                String outSet = "";
                outSet=outSet + ((MobilePhone)n.data).number();
                n=n.next;
                while (n!=null){
                    outSet=outSet +", "+((MobilePhone)n.data).number();
                    n=n.next;
                }
//                return outSet;
                System.out.println(actionMessage + ": "+outSet);
                return null;
            }
            case "findPhone":{
                MobilePhone a = new MobilePhone(Integer.parseInt(arr[1]));
                if((phoneSet.IsMember(a))==true)
                    System.out.println(actionMessage + ": "+findPhone(a).uid);
                else
                    System.out.println(actionMessage + ": "+"Error - No mobile phone with identifier "+Integer.parseInt(arr[1])+" found in the network");
                return null;

            }
            case "lowestRouter":{
                ExchangeList.Exchange a =new ExchangeList.Exchange(Integer.parseInt(arr[1]));
                ExchangeList.Exchange b =new ExchangeList.Exchange(Integer.parseInt(arr[2]));
                System.out.println(actionMessage + ": "+lowestRouter(a,b).uid);
                return null;

            }
            case "findCallPath":{
                MobilePhone a = new MobilePhone(Integer.parseInt(arr[1]));
                MobilePhone b = new MobilePhone(Integer.parseInt(arr[2]));
                LinkedList.Node n = routeCall(a,b).head;
                String outSet = "";
                outSet=outSet + n.data;
                n=n.next;
                while (n!=null){
                    outSet=outSet +", "+n.data;
                    n=n.next;
                }
                if((phoneSet.IsMember(a))==true && (phoneSet.IsMember(b))==true )
                    System.out.println(actionMessage + ": "+outSet);
                if((phoneSet.IsMember(a))==false && (phoneSet.IsMember(b))==true )
                    System.out.println(actionMessage + ": "+"Error - Mobile phone with identifier "+Integer.parseInt(arr[1])+" is currently switched off");
                if((phoneSet.IsMember(a))==true && (phoneSet.IsMember(b))==false )
                    System.out.println(actionMessage + ": "+"Error - Mobile phone with identifier "+Integer.parseInt(arr[2])+" is currently switched off");
                else
                    System.out.println(actionMessage + ": "+"Input valid identifiers");

                return null;
            }
            case "movePhone":{
                MobilePhone a = new MobilePhone(Integer.parseInt(arr[1]));
                ExchangeList.Exchange b =new ExchangeList.Exchange(Integer.parseInt(arr[2]));
                movePhone(a,b);
                return null;
            }
            default:
                return "Enter a valid input";
        }
    }

    public static void main(String[] args ){
        RoutingMapTree C = new RoutingMapTree();
        C.performAction("addExchange 0 1");
        C.performAction("addExchange 0 2");
        C.performAction("addExchange 0 3");
        C.performAction("queryNthChild 0 0");
        C.performAction("queryNthChild 0 2");
        C.performAction("addExchange 1 4");
        C.performAction("addExchange 1 5");
        C.performAction("addExchange 2 6");
        C.performAction("addExchange 2 7");
        C.performAction("addExchange 2 8");
        C.performAction("addExchange 3 9");
        C.performAction("queryNthChild 2 0");
        C.performAction("queryNthChild 3 0");
        C.performAction("switchOnMobile 989 4");
        C.performAction("switchOnMobile 876 4");
        C.performAction("switchOnMobile 876 4");
        C.performAction("queryMobilePhoneSet 4");
        C.performAction("queryMobilePhoneSet 1");
        C.performAction("switchOnMobile 656 5");
        C.performAction("switchOnMobile 54 5");
        C.performAction("queryMobilePhoneSet 1");
        C.performAction("switchOffMobile 54");
        C.performAction("queryMobilePhoneSet 1");
        C.performAction("switchOnMobile 213 6");
        C.performAction("switchOnMobile 568 7");
        C.performAction("switchOnMobile 897 8");
        C.performAction("switchOnMobile 295 8");
        C.performAction("switchOnMobile 346 9");
        C.performAction("queryMobilePhoneSet 0");
        C.performAction("lowestRouter 7 8");
        C.performAction("findPhone 656");
        C.performAction("movePhone 656 4");
        C.performAction("findPhone 656");
        C.performAction("lowestRouter 8 4");
        C.performAction("queryFindCallPath 656 876");
        return;
    }
}
