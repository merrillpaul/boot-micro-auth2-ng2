import * as watch from 'node-watch';
import { processI18nFiles } from './../utils/i18n.json.processor';

const P1_UI_SRC = '../../../application/projectone-ui/src';
/*watch(`${P1_UI_SRC}/app`, {
    recursive: true,
    filter: function (name) {
        return name.indexOf(".json") >= 0;
    }
}).on('change', (event, filename) => {
    console.log(`I18nJson ${filename} changed `);    
    processI18nFiles(`${P1_UI_SRC}/app`, `${P1_UI_SRC}/assets/.i18n`);
});*/

watch(`${P1_UI_SRC}/app`, {
    recursive: true,
    filter: function (name) {
        return name.indexOf(".json") >= 0;
    }
}, function(evt, filename) {
 
  /*if (evt == 'update') {
    // on create or modify 
  }
 
  if (evt == 'remove') {
    // on delete 
  }*/
    console.log(`I18nJson ${filename} event `, evt);    
    processI18nFiles(`${P1_UI_SRC}/app`, `${P1_UI_SRC}/assets/.i18n`);
 
});