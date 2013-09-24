/*
 * Copyright (C) 2013      Denis Forveille titou10.titou10@gmail.com
 * Copyright (C) 2010-2013 Serge Rieder serge@jkiss.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.jkiss.dbeaver.ext.db2.model.cache;

import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.ext.db2.model.fed.DB2RemoteServer;
import org.jkiss.dbeaver.ext.db2.model.fed.DB2RemoteServerOption;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCExecutionContext;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCPreparedStatement;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCStatement;
import org.jkiss.dbeaver.model.impl.jdbc.cache.JDBCObjectCache;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Cache for DB2 Federated Remote Server Options
 * 
 * @author Denis Forveille
 */
public class DB2RemoteServerOptionCache extends JDBCObjectCache<DB2RemoteServer, DB2RemoteServerOption> {

    private static String SQL;

    static {
        StringBuilder sb = new StringBuilder(256);
        sb.append("SELECT *");
        sb.append("  FROM SYSCAT.SERVEROPTIONS");
        sb.append(" WHERE SERVERNAME = ?");// 1
        // DF: Strange. The following columns are supposed to be the PK
        // but in practoce, they are always null...
        // sb.append("   AND WRAPNAME = ?"); // 2
        // sb.append("   AND SERVERTYPE = ?");// 3
        // sb.append("   AND SERVERVERSION = ?");// 4
        sb.append(" ORDER BY OPTION");
        sb.append(" WITH UR");
        SQL = sb.toString();
    }

    @Override
    protected JDBCStatement prepareObjectsStatement(JDBCExecutionContext context, DB2RemoteServer remoteServer) throws SQLException
    {
        final JDBCPreparedStatement dbStat = context.prepareStatement(SQL);
        dbStat.setString(1, remoteServer.getName());
        // dbStat.setString(2, remoteServer.getDb2Wrapper().getName());
        // dbStat.setString(3, remoteServer.getType());
        // dbStat.setString(4, remoteServer.getVersion());
        return dbStat;
    }

    @Override
    protected DB2RemoteServerOption fetchObject(JDBCExecutionContext context, DB2RemoteServer remoteServer, ResultSet resultSet)
        throws SQLException, DBException
    {
        return new DB2RemoteServerOption(remoteServer, resultSet);
    }
}