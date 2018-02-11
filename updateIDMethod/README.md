## Update ID Method

Sometimes due to job demand,  I can not put id (primary key) to the last column after update id column value. 
so I need to recreate table.</br>


#### create table links_temp
create table links_temp(</br>
        id                 int,</br>
	pvalue             double precision,</br>
	bio_entity_1       character varying(255),</br> 
	entity_1_name      character varying(255),</br>
	bio_entity_2       character varying(255),</br>
	entity_2_name      character varying(255),</br> 
	weight             double precision,</br>
	entity_1_type      character varying(255),</br>
	entity_2_type      character varying(255),</br>
	contextid          character varying(255),</br> 
	interaction_type   character varying(255),</br>
	habitat_1          character varying(255),</br> 
	habitat_2          character varying(255)</br>
 )</br>
 
 #### use the program to populate data to links_temp
 
 #### export data from links_temp
 \copy links_temp to '/Users/air/Desktop/temp.csv';</br>
 
 #### create table links, and clarify primary key at this moment
create table links(</br>
        id                 serial primary key  not null,</br>
	pvalue             double precision,</br>
	bio_entity_1       character varying(255),</br> 
	entity_1_name      character varying(255),</br>
	bio_entity_2       character varying(255),</br>
	entity_2_name      character varying(255),</br> 
	weight             double precision,</br>
	entity_1_type      character varying(255),</br>
	entity_2_type      character varying(255),</br>
	contextid          character varying(255),</br> 
	interaction_type   character varying(255),</br>
	habitat_1          character varying(255),</br> 
	habitat_2          character varying(255)</br>
 )</br>
 
 #### import data to links
 \copy links from '/Users/air/Desktop/temp.csv' with DELIMITER E'\t';
 