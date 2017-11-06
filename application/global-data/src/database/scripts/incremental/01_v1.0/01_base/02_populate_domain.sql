insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'ASSESSMENT_TYPE', '1', 'Published', 'ASSESSMENT_TYPE.Published', 'Published');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'ASSESSMENT_TYPE', '2', 'Field Research', 'ASSESSMENT_TYPE.Field.Reseach', 'Field Reseach');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'ASSESSMENT_TYPE', '3', 'Trial', 'ASSESSMENT_TYPE.Trial', 'Trial');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'ASSESSMENT_TYPE', '4', 'Pre-published', 'ASSESSMENT_TYPE.Pre-published', 'Pre-published');


insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'STATUS_TYPE', '1', 'Active', 'ASSESSMENT_STATUS_TYPE.Active', 'Active');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'STATUS_TYPE', '2', 'Inactive', 'ASSESSMENT_STATUS_TYPE.Inactive', 'Inactive');


insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'SCORING_TYPE', '1', 'Scoring', 'SCORING_TYPE.aggregate', 'Aggregate Scoring');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'SCORING_TYPE', '2', 'ComboScoring', 'SCORING_TYPE.combo', 'Combo Scoring');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'SCORING_TYPE', '3', 'Individual', 'SCORING_TYPE.individual', 'Individual Scoring');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'SCORING_TYPE', '3', 'OnTheFly', 'SCORING_TYPE.onthefly', 'On The Fly Scoring');

insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'SCORING_ENGINE', '1', 'OPP And Q-Local Engine', 'SCORING_REPORTING_ENGINE.oppQlocal', 'OPP And Q-Local Engine');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'SCORING_ENGINE', '2', 'Lacrosse', 'SCORING_ENGINE.lacrosse', 'Lacrosse');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'SCORING_ENGINE', '3', 'SAS', 'SCORING_ENGINE.sas', 'SAS');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'SCORING_ENGINE', '4', 'QLocal', 'SCORING_ENGINE.qlocal', 'QLocal');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'SCORING_ENGINE', '5', 'OPP Engine', 'SCORING_REPORTING_ENGINE.opp', 'OPP Engine');

insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'OSA_ENGINE', '1', 'TestNav', 'OSA_ENGINE.TestNav', 'TestNav OSA');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'OSA_ENGINE', '2', 'Qualtrics', 'OSA_ENGINE.Qualtrics', 'Qualtrics OSA');

insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'FORM_TYPE', '1', 'Flex Form', 'FORM_TYPE.Flex.Form', 'Flex Form');

insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'FORM_DELIVERY_TYPE', '1', 'Manual Entry', 'FORM_DELIVERY_TYPE.Manual.Entry', 'Manual Entry');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'FORM_DELIVERY_TYPE', '2', 'On Screen Administration', 'FORM_DELIVERY_TYPE.On.Screen.Administration', 'On Screen Administration');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'FORM_DELIVERY_TYPE', '3', 'Remote On Screen Administration', 'FORM_DELIVERY_TYPE.Remote.On.Screen.Administration', 'Remote On Screen Administration');

insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORTING_ENGINE', '1', 'OPP Engine', 'SCORING_REPORTING_ENGINE.opp', 'OPP Engine');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORTING_ENGINE', '2', 'OPP And Q-Local Engine', 'SCORING_REPORTING_ENGINE.oppQlocal', 'OPP And Q-Local Engine');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORTING_ENGINE', '3', 'RaaS Engine', 'REPORTING_ENGINE.RaaS', 'RaaS Engine');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORTING_ENGINE', '4', 'SLang', 'REPORTING_ENGINE.slang', 'SLang');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORTING_ENGINE', '5', 'QLocal', 'REPORTING_ENGINE.qlocal', 'QLocal');


insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_TYPE', '1', 'Standard', 'REPORT_TYPE.standard', 'Standard (Individual)');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_TYPE', '2', 'Progress', 'REPORT_TYPE.progress', 'Progress');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_TYPE', '3', 'Combo', 'REPORT_TYPE.combo', 'Combo');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_TYPE', '4', 'Group', 'REPORT_TYPE.group', 'Group');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_TYPE', '5', 'Multirater', 'REPORT_TYPE.multirater', 'Multirater');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_TYPE', '6', 'Specialty', 'REPORT_TYPE.specialty', 'Specialty');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_TYPE', '7', 'Progress_Aggregate', 'REPORT_TYPE.progress_aggregate', 'Progress Aggregate');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_TYPE', '8', 'StandardMultiAsmt', 'REPORT_TYPE.standardMultiAsmt', 'Standard (Multi-Assessment)');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_TYPE', '9', 'ProgressMultiAsmt', 'REPORT_TYPE.progressMultiAsmt', 'Progress (Multi-Assessment)');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_TYPE', '10', 'Writer', 'REPORT_TYPE.writer', 'Writer');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_TYPE', '11', 'Standard_Tiered', 'REPORT_TYPE.standard_tiered', 'Standard (Tiered)');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_TYPE', '12', 'Integrated', 'REPORT_TYPE.integrated', 'Integrated');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_TYPE', '13', 'Monitor_Report', 'REPORT_TYPE.monitor_report', 'Monitor Report');

insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_OUTPUT_TYPE', '1', 'Adobe (pdf)', 'REPORT_OUTPUT_TYPE.Adobe.pdf', 'Adobe (pdf)');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_OUTPUT_TYPE', '2', 'Microsoft Excel (xls)', 'REPORT_OUTPUT_TYPE.Microsoft.Excel.xls', 'Microsoft Excel (xls)');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'REPORT_OUTPUT_TYPE', '3', 'Microsoft Word (doc)', 'REPORT_OUTPUT_TYPE.Microsoft.Word.doc', 'Microsoft Word (doc)');

insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'GENDER_TYPE', '1', 'Male', 'GENDER_TYPE.Male', 'Male');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'GENDER_TYPE', '2', 'Female', 'GENDER_TYPE.Female', 'Female');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'GENDER_TYPE', '3', 'Unspecified', 'GENDER_TYPE.Unspecified', 'Unspecified');
insert into DOMAIN (ID, TYPE, ORDERING, CODE, NAME, DESCRIPTION)  values (replace(uuid(),'-',''), 'GENDER_TYPE', '4', 'None', 'GENDER_TYPE.None', 'None');