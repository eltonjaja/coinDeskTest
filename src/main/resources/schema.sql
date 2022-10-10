CREATE TABLE COIN (
    code VARCHAR(10)  NOT NULL ,
    cname VARCHAR(30) NOT NULL  default '',
    symbol VARCHAR(20) NOT NULL default '',
    rate VARCHAR(30) NOT NULL  default '',
    description VARCHAR(200) NOT NULL  default '',
    updateTime VARCHAR(20) NOT NULL  default '',
    PRIMARY KEY (code)
);