1)  This application uses HBase to write data once and read multiple times as per defined criteria

2)  Start hadoop and hbase server from terminal
    2.1) Go to /usr/local/Cellar/hadoop/<hadoop version>/sbin
    2.2) Run $ sh start-all.sh
    2.3) Go to /usr/local/Cellar/hbase/<hbase version>/bin
    2.4) Run $ sh start-hbase.sh
    2.5) Run $ hbase shell

3)  Run CreateNewsFeedTable.java to load data to NewsFeeds table in HBase
4)  Build project
5)  Run GlassFish server in Netbeans
6)  Deploy project which runs index.jsp
7)  Observe that HBase News application loads in default browser
8)  Choose criteria to load news
9)  Select criteria in drop down or enter keyword in input box and Click on Get news.
10) To verify on Terminal, Go back to terminal. Run the following command to check if          
    table 'NewsFeeds' is created
    $list
11) Run the following command to check if the table is populated
    $scan 'NewsFeeds'


Workload Distribution
1) Virtee Parekh
    -   Responsible for figuring out features and advantages of HBase and deciding the aim of the application accordingly.
    -   Worked on connecting HBase with Java through Java API and creating tables and adding data through Java.
    -   Worked on developing queries for the application.
2) Prajakta Gaydhani
    -   Responsible for understanding and studying architecture of HBase, data storage and indexing in HBase
    -   Worked on extracting data from Google News through a Java program with the help of an XML Parser.
    -   Worked on developing queries for the application.
3) Manasi Bharde
    -   Responsible for understanding and studying of query processing and optimization works in HBase.
    -   Worked on connecting HBase with Java through Java API and creating tables and adding data through Java.
    -   Developed the web interface for running queries. 



