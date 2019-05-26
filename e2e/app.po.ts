import { browser, element, by } from 'protractor';

export class MyHomePage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('myhome-root h1')).getText();
  }
}
