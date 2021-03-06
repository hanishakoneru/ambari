/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

var App = require('app');

App.ResultsDownloadController = Ember.ObjectController.extend(App.FileHandler,{
  buttons: [
    {
      title: Em.I18n.t('common.close'),
      action: "cancel",
      classBindings:[':btn',':btn-default']
    }
  ],
  jobResultsLoader:function (output) {
    var jobId = this.get('content.id');
    var url = ['jobs',jobId, 'results','stdout'].join('/');

    return this.fileProxy(url);
  }.property('content'),

  suggestedFilename: function() {
    return this.get("content.jobId").toLowerCase().replace(/\W+/g, "_") + '_results.txt';
  }.property("content.jobId"),

  jobResults:function () {
    return this.get('jobResultsLoader.content.fileContent');
  }.property("content","jobResultsLoader.isPending"),

  actions:{
    download:function () {
      return this.downloadFile(this.get("jobResults"), this.get("suggestedFilename"));
    }
  }
});
