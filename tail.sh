#!/bin/bash
multitail --follow-all --retry-all --mergeall -CS liberty -iw notes-application/target/liberty/wlp/usr/servers/defaultServer/logs/trace.log 2 -iw notes-listener/target/liberty/wlp/usr/servers/defaultServer/logs/trace.log 2 