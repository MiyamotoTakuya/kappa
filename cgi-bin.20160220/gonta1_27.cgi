#!/usr/local/bin/perl
require "./cgi-lib.pl";
require "./jcode.pl";

ReadParse();

print "Content-type: text/html\n\n";
print "<HTML>\n";
print "<HEAD>\n";
print "</HEAD>\n";
print "<BODY>\n";
jcode::convert($in{"answer"},"euc","","z");

if ($in{"answer"} eq "しおひがり"){
	print jcode::sjis("「ふむ、ブラボーじゃ！！」<BR>","euc");
}else {
	print jcode::sjis("「むむぅ、その言葉は知らんのう。」<BR>","euc");
}
print "</BODY>\n";
print "</HTML>\n";
