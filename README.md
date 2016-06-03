# SupSimStock
Super Stock

Assignment Super Simple Stocks

Requirements
	Provide working source code that will :-
	For a given stock, 
	calculate the dividend yield
	calculate the P/E Ratio
	record a trade, with timestamp, quantity of shares, buy or sell indicator and price
	Calculate Stock Price based on trades recorded in past 15 minutes
	Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
Constraints & Notes
	Written in one of these languages:
	Java, C#, C++, Python
	No database or GUI is required, all data need only be held in memory
	Formulas and data provided are simplified representations for the purpose of this exercise
Table1. Sample data from the Global Beverage Corporation Exchange

Stock Symbol	Type	Last Dividend	Fixed Dividend	Par Value	
TEA	Common	0		100	
POP	Common	8		100	
ALE	Common	23		60	
GIN	Preferred	8	2%	100	
JOE	Common	13		250	
All number values in pennies
Table 2. Formula
	Common	Preferred
Dividend Yield	(Last Dividend)/(Ticker Price)	(Fixed Dividend .Par Value)/(Ticker Price)
P/E Ratio	(Ticker Price)/Dividend
Geometric Mean	√(n&p_1 p_2 p_3…p_n )
Stock Price	(∑_i▒〖 Trade 〖Price〗_i×〖Quantity〗_i 〗)/(∑_i▒〖Quantity〗_i )

Solution:

Opted for a service approach and exposed SimpleStockService to do various operations given in the problem statement. Request flows through controller(main java class but can be a real web controller) to service layer to persistence layer.
It's Spring & Maven based java application to record the stock & trading data via spring xml files under resources folder.
Then all operation defined in the service layer would access this data and return the results after doing the calculations required.

How to Run:

Maven build the application and run assembly:assembly goal to generate a stand alone jar which can be run separately using a bat file, Also attached the generated zip file stock-service [Sandeep_Lohani] .zip, 
Double click on start.bat file which will first record all trades and then do the desired operations.  
Logs are generated at ./logs/Logfile.log, Junit tests also included.




  

