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

if ($in{"answer"} eq "�����Ђ���"){
	print jcode::sjis("�u�ӂށA�u���{�[����I�I�v<BR>","euc");
}else {
	print jcode::sjis("�u�ނނ��A���̌��t�͒m���̂��B�v<BR>","euc");
}
print "</BODY>\n";
print "</HTML>\n";
