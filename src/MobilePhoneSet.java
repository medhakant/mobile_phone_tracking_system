public class MobilePhoneSet extends Myset {
    public void MobilePhoneSet(){
        MobilePhoneSet m=new MobilePhoneSet();
    }

    public  Boolean IsMember(Object e){
        LinkedList.Node n =llist.head;
        while (n != null)
        {
            if(((MobilePhone)n.data).number()==((MobilePhone) e).number())
                return true;
            n=n.next;
        }
        return false;

    }

    public void Insert(Object o){
        LinkedList.Node nrear =llist.head;
        if (IsMember(o)==true)
            return;
        else if (nrear!=null && IsMember(o)==false){
            LinkedList.Node nfront =nrear.next;
            while (nfront!=null){
                if (((MobilePhone)nrear.data).number()==((MobilePhone) o).number())
                    return;
                nfront=nfront.next;
                nrear=nrear.next;
            }
            LinkedList.Node newNode = new LinkedList.Node(o);
            nrear.next=newNode;
        }
        else
            llist.head =new LinkedList.Node(o);

    }

    public void Delete(Object o) throws Exception{
        try {
            LinkedList.Node nrear = llist.head;
            LinkedList.Node nfront = nrear.next;
            if (((MobilePhone) nrear.data).number() ==((MobilePhone) o).number()){
                llist.head=nfront;
                return ;
            }
            while (nfront!=null){
                if (((MobilePhone) nfront.data).number()==((MobilePhone) o).number()){
                    nrear.next = nfront.next;
                    return;
                }
                nfront=nfront.next;
                nrear=nrear.next;
            }
            if (nfront==null)
                throw new Exception("Exception: Object not in MySet");
        }catch(NullPointerException e) {}
    }

    public MobilePhoneSet Union(MobilePhoneSet a){
        MobilePhoneSet u = new MobilePhoneSet();
        LinkedList.Node n = llist.head;
        while (n!=null){
            u.Insert(n.data);
            n=n.next;
        }
        n = a.llist.head;
        while (n!=null){
            u.Insert(n.data);
            n=n.next;
        }
        return u;
    }

    public MobilePhoneSet Intersection(MobilePhoneSet a){
        MobilePhoneSet i = new MobilePhoneSet();
        LinkedList.Node n = a.llist.head;
        while (n!=null){
            if (IsMember(n.data)==true)
                i.Insert(n.data);

            n=n.next;
        }
        return i;
    }


//    public static void main(String[] args){
//        MobilePhoneSet m=new MobilePhoneSet();
//        MobilePhone a=new MobilePhone(978);
//        MobilePhone b=new MobilePhone(678);
//        MobilePhone c=new MobilePhone(879);
//        MobilePhone d=new MobilePhone(879);
//        m.Insert(a);
//        a.switchOn();
//        b.switchOff();
//        m.Insert(b);
//        m.Insert(c);
//        m.Insert(d);
//        MobilePhoneSet k=new MobilePhoneSet();
//        MobilePhone z=new MobilePhone(879);
//        MobilePhone l=new MobilePhone(679);
//        MobilePhone x=new MobilePhone(678);
//        MobilePhone y=new MobilePhone(879);
//        k.Insert(l);
//        k.Insert(x);
//        k.Insert(y);
//        k.Insert(z);
//        LinkedList.Node n =m.llist.head;
//        while (n!=null){
//            System.out.println(((MobilePhone)n.data).number());
//            System.out.println(((MobilePhone)n.data).status());
//            n = n.next;
//        }
//    }
}
