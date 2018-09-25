A design of a data structure that will help
us solve a simplied version of the mobile phone tracking problem, i.e., the
fundamental problem of cellular networks: When a phone is called, nd where
it is located so that a connection may be established.

The program makes use of a simple linked list and for some purposes doubly linked list. Also, from the linked list i have made a abstract data type "MySet" with all the function of a mathematical set. The code for it is in the Myset class. Then there are the mobilephone object and Exchange object. And a mobilephone set and a exchangeList. MobilePhone set extends MySet and has all it's functions. Whereas ExchangeList is a simple linked list of Exchanges but with a tree structure.

For more details of the problem read the pdf file of the problem statement and to get an idea look at the tree structure image.

To test the program you can run the "checker" class, which takes input from action.txt and the outputs should match with the answers.txt or you can otherwise run the "RoutingMapTree" class and manually input actions in it's main class.
